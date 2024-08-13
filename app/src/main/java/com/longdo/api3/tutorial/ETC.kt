package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel

@Composable
fun _3DObject() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.run.value =
            """
            const scale = 100;
            const data = [{
                coordinates: [100.52934, 13.72252, 0],
                scale: [scale,scale,scale],
                color: [255, 255, 0, 255],
                translation: [0, 0, scale/2]
            }];
    const layer = new deck.MapboxLayer({
        id: 'scenegraph-layer',
        type: deck.ScenegraphLayer,
        data,
        scenegraph: 'https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Box/glTF-Binary/Box.glb',
        getPosition: d => d.coordinates,
        getScale: d => d.scale,
        getTranslation: d => d.translation,
        getColor: d => d.color,
        opacity: 0.5,
        _lighting: 'pbr',
        parameters: {
            depthTest: false
        }
    });
    map.Layers.insert("", layer);
        """
    }) {
        Text(text = "3D")
    }
}

@Composable
fun HoverEffect() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        val geocode = "10__"
        val dataset = "IG"
        val apiKey = vm.const.value?.get("apiKey") as String? ?: ""
        var hoveredStateId: Int = -1
        vm.run.value =
            """
          map.Renderer.addSource('states', {
            type: 'geojson',
            generateId: true,
            data: `https://api.longdo.com/map/services/object?mode=geojson&id=${geocode}&dataset=${dataset}&key=${apiKey}`
          });

          // The feature-state dependent fill-opacity expression will render the hover effect
          // when a feature's hover state is set to true.
          map.Renderer.addLayer({
            id: 'state-fills',
            type: 'fill',
            source: 'states',
            layout: {},
            paint: {
              'fill-color': '#627BC1',
              'fill-opacity': [
                'case',
                ['boolean', ['feature-state', 'hover'], false],
                1,
                0.5
              ]
            }
          });

          map.Renderer.addLayer({
            id: 'state-borders',
            type: 'line',
            source: 'states',
            layout: {},
            paint: {
              'line-color': '#627BC1',
              'line-width': 2
            }
          });

          map.Renderer.on('click', 'state-fills', function (e) {
            e.lngLat.lon = e.lngLat.lng
            var popup = new longdo.Popup(e.lngLat,{title:e.features[0].properties.name_t})
            map.Overlays.add(popup)
          });

          // When the user moves their mouse over the state-fill layer, we'll update the
          // feature state for the feature under the mouse.
          map.Renderer.on('mousemove', 'state-fills', function (e) {
            if (e.features.length > 0) {
              if (${hoveredStateId}) {
                map.Renderer.setFeatureState(
                  { source: 'states', id: ${hoveredStateId} },
                  { hover: false }
                );
              }
              ${hoveredStateId} = e.features[0].id;
              map.Renderer.setFeatureState(
                { source: 'states', id: ${hoveredStateId} },
                { hover: true }
              );
            }
          });

          // When the mouse leaves the state-fill layer, update the feature state of the
          // previously hovered feature.
          map.Renderer.on('mouseleave', 'state-fills', function () {
            if (${hoveredStateId}) {
              map.Renderer.setFeatureState(
                { source: 'states', id: ${hoveredStateId} },
                { hover: false }
              );
            }
            hoveredStateId = null;
          });
            """
    }) {
        Text(text = "HoverEffect")
    }
}
