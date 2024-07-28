package contracts
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method("POST")
        url("/api/songs")
        headers {
            contentType(applicationJson())
        }
        body("""
            {
                "name" : "Test Name",
                "artist" : "Test Artist",
                "album" : "Test Album",
                "length" : "3:30",
                "resourceId" : 1,
                "year" : 2024
            }
       """)
    }
    response {
        status(201)
        body("""
        {
        "id" : 1
        }
        """)
    }
}
