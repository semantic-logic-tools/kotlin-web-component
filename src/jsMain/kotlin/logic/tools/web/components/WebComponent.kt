package logic.tools.web.components

import org.w3c.dom.*
import org.w3c.dom.css.CSSStyleDeclaration

abstract external class WebComponent : HTMLElement {
    abstract fun connectedCallback()
    abstract fun disconnectedCallback()
    abstract fun adoptedCallback()
    abstract fun attributeChangedCallback(name: String, oldValue: String, newValue: String)

    // from HTMLElement
    override val style: CSSStyleDeclaration
    override fun after(vararg nodes: dynamic)
    override fun before(vararg nodes: dynamic)
    override fun remove()
    override fun replaceWith(vararg nodes: dynamic)
    override var contentEditable: String
    override val isContentEditable: Boolean
    override fun convertPointFromNode(point: DOMPointInit, from: dynamic, options: ConvertCoordinateOptions): DOMPoint
    override fun convertQuadFromNode(quad: dynamic, from: dynamic, options: ConvertCoordinateOptions): DOMQuad
    override fun convertRectFromNode(rect: DOMRectReadOnly, from: dynamic, options: ConvertCoordinateOptions): DOMQuad
    override fun getBoxQuads(options: BoxQuadOptions): Array<DOMQuad>
    override val childElementCount: Int
    override val children: HTMLCollection
    override fun append(vararg nodes: dynamic)
    override fun prepend(vararg nodes: dynamic)
    override fun querySelector(selectors: String): Element?
    override fun querySelectorAll(selectors: String): NodeList
}

open class BaseWebComponent : WebComponent() {
    override fun connectedCallback() {}
    override fun disconnectedCallback() {}
    override fun adoptedCallback() {}
    override fun attributeChangedCallback(name: String, oldValue: String, newValue: String) {}
}



fun defineWebComponent(tagName: String, wc: JsClass<out WebComponent>) {
    val observedAttributes = (wc.asDynamic().Companion?.observedAttributes?.call() as? Array<String>) ?: emptyArray<String>()
    defineWebComponent(tagName, wc, observedAttributes)
}

//external fun defineWebComponent(tagName: String, wc: JsClass<out WebComponent>, observedAttributes: Array<String>)
private fun defineWebComponent(tagName: String, wc: JsClass<out WebComponent>, observedAttributes: Array<String>) {
    function<(String, JsClass<out WebComponent>, Array<String>) -> () -> Unit>("tagName", "proto", "observedAttributes", block = """
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


