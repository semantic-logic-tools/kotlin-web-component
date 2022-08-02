package logic.tools.web.components

@OptIn(ExperimentalStdlibApi::class)
@EagerInitialization
val dummyJustToRunEagerly  = run {
    js("globalThis.WebComponent = function() {}")
}



fun _defineWebComponent(tagName: String, wc: JsClass<*>) {
    val observedAttributes = (wc.asDynamic().Companion?.observedAttributes?.call() as? Array<String>) ?: emptyArray<String>()
    defineWebComponent(tagName, wc, observedAttributes)
}

//external fun defineWebComponent(tagName: String, wc: JsClass<out WebComponent>, observedAttributes: Array<String>)
private fun defineWebComponent(tagName: String, wc: JsClass<*>, observedAttributes: Array<String>) {
    function<(String, JsClass<*>, Array<String>) -> () -> Unit>("tagName", "proto", "observedAttributes", block = """
    globalThis.WebComponent = function() {}
    let wc = class extends HTMLElement {
        constructor() {
            super();
            proto.prototype.constructor.call(this);
        }
        static get observedAttributes() { return observedAttributes; }
    }
    Object.assign(wc.prototype, proto.prototype);
    window.customElements.define(tagName, wc);
""")(tagName, wc, observedAttributes)
}

@JsName("Function")
private external fun <T> function(vararg params: String, block: String): T


