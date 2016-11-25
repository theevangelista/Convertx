echo 'Preparing for Instrumented Tests...'
echo 'Starting AVD $1'
./scripts/run-avd.sh $1
echo 'Executing Instrumented Tests...'
./gradlew deviceAndroidTest # Installs and runs instrumentation tests using all Device Providers.
# Optionaly you can use
# connectedAndroidTest - Installs and runs instrumentation tests for all flavors on connected devices.
echo '--- DONE executing Instrumented Tests for $1 ---'
