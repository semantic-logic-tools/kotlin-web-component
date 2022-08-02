
function WebComponent() {}

function defineWebComponent(tagName, proto, observedAttributes) {
    let wc = class extends HTMLElement {
        constructor() {
            super();
            proto.prototype.constructor.call(this);
        }
        static get observedAttributes() { return observedAttributes; }
    }
    Object.assign(wc.prototype, proto.prototype);
    window.customElements.define(tagName, wc);
}