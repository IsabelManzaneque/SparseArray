# SparseArray
Two different implementations of my abstract data type: Sparse Array

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)

## General info

Arrays are a very useful data structure when we have elements that can be indexed, as the time complexity to access these elements will be O(1) no matter the input size. But there are many occasions when using arrays involves an excessive memory usage and allocation (for instance, when very few indexes of the total available are being used and these are quite separated from each other). In cases like these, a Sparse Array structure is more useful as we can also store the date using an index but there is no memory allocation for all the elements that can be indexed. When using these structures, the time complexity to access the elements will not be constant but the memory allocation won't be so wasteful. 

For this project, I have developed two different implementations of a Sparse Array in which the elements of the array are stored as indexed pairs: <index, value>. The first implementation will use a sequence support structure to store the indexed pairs while the second one will use a binary tree support structure. The binary tree implementation will use the binary representation of its index to store and find the elements in the structure.

The project includes the following:

* SparseArrayIF: The sparse array interface
* SparseArraySequence: Sequence implementation of the sparse array
* SparseArrayBTree: Binary tree implementation of the sparse array
* IndexedPair: Node structure formed by an index and a value


## Technologies
Project is created with:
* Java 17
* Eclipse IDE 2021-03

