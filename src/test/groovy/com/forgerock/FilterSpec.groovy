package com.forgerock

import com.forgerock.expressions.EqualExpression
import com.forgerock.expressions.Expression
import com.forgerock.expressions.ExpressionWithAnd
import com.forgerock.expressions.FilterData
import com.forgerock.expressions.GreaterThanExpression
import spock.lang.Specification
import spock.lang.Unroll

class FilterSpec extends Specification {
    private Filter testObj

    @Unroll("find user with role equal to administrator #key #targetData #expectedResult")
    def "find user with role equal to administrator"() {
        given: "a simple equal Expression"
            FilterData filterData = new FilterData(key, targetData)
            Expression equalExpression = new EqualExpression(filterData)
        and:
            testObj = new Filter(equalExpression)
        when:
            boolean result = testObj.match(getUserData())
        then:
            result == expectedResult
        where:
            key    | targetData      | expectedResult
            "role" | "administrator" |  true
            "role" | "secretary"     |  false
    }

    def "find user with age greater than 30"() {
        given: "a greater than expression"
            FilterData filterData = new FilterData(key, targetData)
            Expression greateThanExpression = new GreaterThanExpression(filterData)
        and:
            testObj = new Filter(greateThanExpression)
        when:
            boolean result = testObj.match(getUserData())
        then:
            result == expectedResult
        where:
            key    | targetData      | expectedResult
            "age"  | "25"            |  false
            "age"  | "35"            |  true
            "age"  | "30"            |  false
    }

    def "find administrator that older than 30: complex filter"() {
        given: "a greater than expression"
            FilterData greaterThanfilterData = new FilterData(greaterKey, greaterData)
            Expression greaterThanExpression = new GreaterThanExpression(greaterThanfilterData)
        and:
            FilterData equalfilterData = new FilterData(equalKey, equalData)
            Expression equalExpression = new EqualExpression(equalfilterData)
        and:
            Expression andExpression = new ExpressionWithAnd(equalExpression, greaterThanExpression)

            testObj = new Filter(andExpression)
        when:
            boolean result = testObj.match(getUserData())
        then:
            result == expectedResult
        where:
            greaterKey | greaterData | equalKey | equalData       | expectedResult
             "age"     |  "35"       | "role"   | "administrator" |  true
             "age"     |  "35"       | "role"   | "Administrator" |  true
             "age"     |  "30"       | "role"   | "administrator" |  false
             "age"     |  "35"       | "role"   | "accountant"    |  false
             "age"     |  "abc"      | "role"   | "administrator" |  false
    }

    private LinkedHashMap getUserData() {
        return [
                "firstname": "Joe",
                "surname": "Blogs",
                "role": "administrator",
                "age": "30"
        ]
    }
}