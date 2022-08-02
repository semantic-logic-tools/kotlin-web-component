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
    _defineWebComponent(tagName, wc)
}

