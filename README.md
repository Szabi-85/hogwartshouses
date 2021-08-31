# Hogwarts Houses

## How to run in IntelliJ:

1. import as a Maven project
2. create run/debug configuration
    - create maven template
    - choose working directory
    - set environment variables to `dao_impl=?` where ? is
        - `memory` for memory dao implementation usage
        - `jdbc` for jdbc dao implementation usage
        - `jpa` for jpa dao implementation usage
    - name your configuration
    - press apply and ok button
3. run configuration
4. open `localhost:8080` in the browser