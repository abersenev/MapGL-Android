package ru.dublgis.dgismobile.sdktestapp

import android.graphics.Color
import android.net.Uri
import ru.dublgis.dgismobile.mapsdk.labels.Label
import ru.dublgis.dgismobile.mapsdk.labels.LabelImage
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import ru.dublgis.dgismobile.mapsdk.utils.icon.ImageFactory

class LabelsActivity : MapActivity() {

    private var label: Label? = null

    override fun onDGisMapReady() {
        showLabel()
    }

    private fun showLabel() {

        val drawId = resources.getIdentifier("icon_adaptive_background", "drawable", packageName)
        val path =
            Uri.parse("android.resource://$packageName/$drawId")

        val labelImage = LabelImage(
            //image = IconFactory(this).fromAsset("tooltip-big.svg"),
            /*image = ImageFactory(this).fromBitmap(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.icon_adaptive_background
                )
            ),*/
            image = ImageFactory(this).fromResource(R.drawable.icon_adaptive_background),
            size = 500 to 250,
            stretchX = listOf(50 to 200, 300 to 450),
            stretchY = listOf(50 to 150),
            padding = listOf(50, 50, 100, 50)
        )
        label =
            map?.createLabel(
                LabelOptions(
                    coordinates = map?.center!!,
                    text = "This is label",
                    color = Color.BLUE,
                    fontSize = 24f,
                    maxZoom = 14f,
                    minZoom = 10f,
                    anchor = 15.0 to 48.0,
                    image = labelImage
                )
            )

        map?.setOnClickListener {
            if (label?.isHidden!!)
                label?.show()
            else
                label?.hide()
        }
    }
}