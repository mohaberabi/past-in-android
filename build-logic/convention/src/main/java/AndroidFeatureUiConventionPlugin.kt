import com.mohaberabi.convention.addUiLayerDepencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {


        target.run {
            pluginManager.run {
                apply("pia.android.library.compose")
            }
            dependencies {
                addUiLayerDepencies(target)
            }
        }

    }
}