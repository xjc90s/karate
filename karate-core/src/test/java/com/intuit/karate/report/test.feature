Feature: feature name

Background:
# background comment
* print 'in background'

@one
Scenario: first one
* print 'in first one'
* def foo = 'no log'
* call read('called.feature') { dummy: 'arg' }

@one @two
Scenario: second one
* print 'in second one'
# some comment
* def bar = 'no log'
* table data
    | foo | bar |
    |   1 |   2 |
    |   3 |   4 |
* def large = 
"""
{ 
  one: 'first', 
  two: 'second'
}
"""
* print large

Scenario Outline: example ${data}
# outline comment
* print 'in outline'

Examples:
| data |
| 1    | 
| 2    |

Scenario Outline: dynamic ${__num + 1}
# dynamic outline comment
* print 'row:', __row

Examples:
| read('data.csv') |

Scenario: calling feature in loop
* def data = [{ name: 'one' }, { name: 'two' }]
* call read('called-loop.feature') data

@setup
Scenario:
* def data = [{a: 1}, {a: 2}]

Scenario Outline:
* print __row

Examples:
| karate.setup().data |