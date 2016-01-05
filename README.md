## Eclipse plug-in to format fluid-style 'one-liners' to separate lines.

Before:
```
builder.foo(1).bar(2).foo(3).build();
```

After:
```
builder //
	.foo(1) //
	.bar(2) //
	.foo(3) //
	.build();
```

### Installation
* Copy [com.o3tt3rli.fluidformat_0.8.0.jar](https://github.com/krizzdewizz/fluidformat/releases/download/v0.8.0/com.o3tt3rli.fluidformat_0.8.0.jar) to your Eclipse `plugins` or `dropins` folder and restart Eclipse.
* In the Java editor, select a range of text or place the cursor somewhere inside a fluid expression and hit `Ctrl+6`.

You may want to reassign the key binding in the `Keys` preference page. 
