# [YAMG] Yet Another Mapper Generator

// **TODO:** add badges

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


## About

**YAMG** (Yet Another Mapper Generator) is a KSP written plugin; its main goal to avoid boilerplate code for mappers between (Data ↔️ Domain ↔️ UI).

YAMG supports two ways of mapper implementation - method way (`to[Layer]Model()`) and a class way (`*[LayerA]to[LayerB]ModelMapper`).

## How it works?

So, to make it works, firstly you need to decide which way of mapping you prefer. Let's start with extentsion method way.

### Extension method

To map a model in a such way you need to use `@YamgExt` annotation. The only one requirement - fields orders and theirs types have to be identical; in the future, there might be some strategies of mappings (currently, WIP).

To make it clear, there is a little example of usage:

```java
// Our "target" class
data class FooUiModel(
    val fieldOne: String,
    val fieldTwo: Int,
    val fieldThree: Boolean,
    val fieldFour: Short,
) : UiMapperModel

// Source class with mapping configuration 
@YamgExt(
    targetClass = FooUiModel::class,
)
data class FooDomain(
    val fieldOne: String?,
    val fieldTwo: Int?,
    val fieldThree: Boolean,
    val fieldFour: Short?,
) : DomainMapperModel


fun main(){
	val fooDomain = FooDomain("", 1, false, 1)
	fooDomain.toFooUiModel()
}
```

Under the hood, in a `/ksp` folder we gonna have such code:

```java
public fun FooDomain.toFooUiModel(): FooUiModel = FooUiModel(
	fieldOne?:"",
	fieldTwo?:0,
	fieldThree,
	fieldFour?:0,
)
```


## Installation


## Tests

Tests were written using [Kotest](https://kotest.io/).

You can take a look at them in `:app` module.

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