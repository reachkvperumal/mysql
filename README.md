# mysql
mysql demo with jdbc template
For Actuator hit following URI in GET mode
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/actuator",
            "templated": false
        },
        "auditevents": {
            "href": "http://localhost:8080/api/actuator/auditevents",
            "templated": false
        },
        "beans": {
            "href": "http://localhost:8080/api/actuator/beans",
            "templated": false
        },
        "caches-cache": {
            "href": "http://localhost:8080/api/actuator/caches/{cache}",
            "templated": true
        },
        "caches": {
            "href": "http://localhost:8080/api/actuator/caches",
            "templated": false
        },
        "health-component": {
            "href": "http://localhost:8080/api/actuator/health/{component}",
            "templated": true
        },
        "health-component-instance": {
            "href": "http://localhost:8080/api/actuator/health/{component}/{instance}",
            "templated": true
        },
        "health": {
            "href": "http://localhost:8080/api/actuator/health",
            "templated": false
        },
        "conditions": {
            "href": "http://localhost:8080/api/actuator/conditions",
            "templated": false
        },
        "configprops": {
            "href": "http://localhost:8080/api/actuator/configprops",
            "templated": false
        },
        "env": {
            "href": "http://localhost:8080/api/actuator/env",
            "templated": false
        },
        "env-toMatch": {
            "href": "http://localhost:8080/api/actuator/env/{toMatch}",
            "templated": true
        },
        "info": {
            "href": "http://localhost:8080/api/actuator/info",
            "templated": false
        },
        "loggers": {
            "href": "http://localhost:8080/api/actuator/loggers",
            "templated": false
        },
        "loggers-name": {
            "href": "http://localhost:8080/api/actuator/loggers/{name}",
            "templated": true
        },
        "heapdump": {
            "href": "http://localhost:8080/api/actuator/heapdump",
            "templated": false
        },
        "threaddump": {
            "href": "http://localhost:8080/api/actuator/threaddump",
            "templated": false
        },
        "metrics-requiredMetricName": {
            "href": "http://localhost:8080/api/actuator/metrics/{requiredMetricName}",
            "templated": true
        },
        "metrics": {
            "href": "http://localhost:8080/api/actuator/metrics",
            "templated": false
        },
        "scheduledtasks": {
            "href": "http://localhost:8080/api/actuator/scheduledtasks",
            "templated": false
        },
        "httptrace": {
            "href": "http://localhost:8080/api/actuator/httptrace",
            "templated": false
        },
        "mappings": {
            "href": "http://localhost:8080/api/actuator/mappings",
            "templated": false
        }
    }
}


