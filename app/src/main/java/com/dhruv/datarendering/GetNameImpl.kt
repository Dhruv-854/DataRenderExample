package com.dhruv.datarendering

class GetNameImpl : GetName {
    override fun getName(): List<ExampleData> {
        return listOf(
            ExampleData("name1"),
            ExampleData("name2"),
            ExampleData("name3"),
            ExampleData("name4"),
        )
    }
}