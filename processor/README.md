# [YAMG] Yet Another Mapper Generator

          _____                    _____                    _____                    _____
         |\    \                  /\    \                  /\    \                  /\    \
         |:\____\                /::\    \                /::\____\                /::\    \
         |::|   |               /::::\    \              /::::|   |               /::::\    \
         |::|   |              /::::::\    \            /:::::|   |              /::::::\    \
         |::|   |             /:::/\:::\    \          /::::::|   |             /:::/\:::\    \
         |::|   |            /:::/__\:::\    \        /:::/|::|   |            /:::/  \:::\    \
         |::|   |           /::::\   \:::\    \      /:::/ |::|   |           /:::/    \:::\    \
         |::|___|______    /::::::\   \:::\    \    /:::/  |::|___|______    /:::/    / \:::\    \
         /::::::::\    \  /:::/\:::\   \:::\    \  /:::/   |::::::::\    \  /:::/    /   \:::\ ___\
        /::::::::::\____\/:::/  \:::\   \:::\____\/:::/    |:::::::::\____\/:::/____/  ___\:::|    |
       /:::/~~~~/~~      \::/    \:::\  /:::/    /\::/    / ~~~~~/:::/    /\:::\    \ /\  /:::|____|
      /:::/    /          \/____/ \:::\/:::/    /  \/____/      /:::/    /  \:::\    /::\ \::/    /
     /:::/    /                    \::::::/    /               /:::/    /    \:::\   \:::\ \/____/
    /:::/    /                      \::::/    /               /:::/    /      \:::\   \:::\____\
    \::/    /                       /:::/    /               /:::/    /        \:::\  /:::/    /
     \/____/                       /:::/    /               /:::/    /          \:::\/:::/    /
                                  /:::/    /               /:::/    /            \::::::/    /
                                 /:::/    /               /:::/    /              \::::/    /
                                 \::/    /                \::/    /                \::/____/
                                  \/____/                  \/____/

## :yamg:processor

### Extension method mapper

#### Annotation

To make a KSP generate mapper method, firstly, we need to mark our class with a `YamgExt`
annotation.

`YamgExt` contains next params:

- `targetClass` - the class that will be used as a mapping result;
- `methodName` - extension method name;
- `excludeFields` - fields to exclude in parent class.

#### Example of usage

The main idea is to generate extension method for mapping.

Imagine, we have a class `FooDomainModel` and we need to map it to UI model (`FooUiModel`).

To fulfill this, we need to use `@YamgExt` annotation.

An example of usage:

```java
@YamgExt(
        targetClass = FooUiModel::class,
        methodName = "toUiModel"
)
data class FooDomain(
        val fieldOne:String?,
        val fieldTwo:Int,
        ) :DomainMapperModel
```

As a result, `/build/generated/ksp/debug/kotlin/dev.yamg.app/FooDomainExt.kt` contains follow code:

```java
package dev.yamg.app

import dev.yamg.app.ui.FooUiModel

public fun FooDomain.toUiModel():FooUiModel=FooUiModel(
        fieldOne?:"",
        fieldTwo,
        )
```

## License

```
MIT License

Copyright (c) 2023 Andrew Malitchuk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```