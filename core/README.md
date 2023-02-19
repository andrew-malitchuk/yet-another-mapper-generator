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

## :yamg:core

## Structure

### Models

According to the Clean Architecture, there are three levels/layers of models - Data, Domain and UI.

Each of them has own presentation in YAMG:
- `BaseMapperModel`;
- `DataMapperModel`;
- `DomainMapperModel`;
- `UiMapperModel`.

> To avoid problems in naming, each levels model has "unique" (i hope) naming
> patter `<Layer>MapperModel`.

Also, there is a base model - `BaseMapperModel`.

So, hierarchy diagram is follow:

// **TODO:** hierarchy diagram

### Mappers

As you know, there are two ways how to map an object: via method which is specified in a class (f.e `toDomain()`)
and via mapper classes (f.e. `domainUiMapper.mapTo(foobar)`).

#### Classes

On a top of the hierarchy, there are `BaseModelMapper` and `BaseListMapper`.

According to name it's clear, that `BaseModelMapper` is for single model mappers and `BaseListMapper` is for lists.

Hierarchy is follow:

// **TODO:** hierarchy diagram

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
