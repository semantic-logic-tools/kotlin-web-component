
# Kotlin Web Components

Allows to write custom web components using Kotlin.


To define your web component, create a class, either inheriting `io.github.semantic-logic-tools.web.components.WebComponent` or `io.github.semantic-logic-tools.web.components.BaseWebComponent` 
which has empty implementations for `connectedCallback`, `disconnectedCallback`, `adoptedCallback`, `attributeChangedCallback`.


```kotlin
@JsExport
class MyWebComponent : BaseWebComponent() {
    init {
        val shadow = this.attachShadow(ShadowRootInit(ShadowRootMode.OPEN)).unsafeCast<HTMLElement>()
        shadow.innerHTML = "<p>Hello</p>"
    }

    override fun connectedCallback() {
        window.setTimeout( { this.setAttribute("name", "World") }, 3000)
    }

    override fun attributeChangedCallback(name: String, oldValue: String, newValue: String) {
        window.alert(name + newValue)
    }

    companion object {
        @JsName("observedAttributes")
        fun observedAttributes() = arrayOf("name")
    }
}
```

```kotlin
defineWebComponent("hello-world", MyWebComponent::class.js)
```


Note that the point of this module is not to provide any functionality, but simply work around the lack of Kotlin JS support for web-components (requires ES6).

There are probably smarter ways to do this, please contribute.

Hopefully, this module becomes useless at some point (when Kotlin JS supports ES6, for instance?).


## References & Inspiration

- [https://discuss.kotlinlang.org/t/creating-a-custom-element-webcomponent]()
- [https://stackoverflow.com/questions/45747646/what-is-the-es5-way-of-writing-web-component-classes]()
- [https://youtrack.jetbrains.com/issue/KT-8373]()
