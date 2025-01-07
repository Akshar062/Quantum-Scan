package org.akshar.quantum_scan.bottomNavigationWidgets

import platform.CoreImage.*
import platform.Foundation.*


actual fun getPlatformRenderEffect(): Any {
    // Create a Core Image blur filter
    val blurFilter = CIFilter.filterWithName("CIGaussianBlur") ?: error("CIGaussianBlur not found")
    blurFilter.setDefaults()
    blurFilter.setValue(80.0, forKey = "inputRadius")

    // Create a color matrix filter for color adjustments
    val colorMatrixFilter = CIFilter.filterWithName("CIColorMatrix") ?: error("CIColorMatrix not found")
    colorMatrixFilter.setDefaults()

    // Adjust the color matrix values
    val rVector = CIVector(1.0,0.0,0.0,0.0)
    val gVector = CIVector(0.0,1.0,0.0,0.0)
    val bVector = CIVector(0.0,0.0,1.0,0.0)
    val aVector = CIVector(0.0,0.0,0.0,50.0)
    val biasVector = CIVector(-5000.0,0.0,0.0,0.0)

    // Set the color vectors and apply them
    colorMatrixFilter.setValue(rVector, forKey = "inputRVector")
    colorMatrixFilter.setValue(gVector, forKey = "inputGVector")
    colorMatrixFilter.setValue(bVector, forKey = "inputBVector")
    colorMatrixFilter.setValue(aVector, forKey = "inputAVector")
    colorMatrixFilter.setValue(biasVector, forKey = "inputBiasVector")

    // Chain the filters together
    colorMatrixFilter.setValue(blurFilter.outputImage, forKey = "inputImage")

    return colorMatrixFilter
}