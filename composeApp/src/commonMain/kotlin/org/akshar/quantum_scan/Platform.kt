package org.akshar.quantum_scan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform