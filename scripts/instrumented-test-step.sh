echo 'Preparing for Instrumented Tests...'
echo "Starting AVD"
./scripts/run-avd.sh
echo 'Executing Instrumented Tests...'
./gradlew connectedAndroidTest
echo "--- DONE executing Instrumented Tests ---"
