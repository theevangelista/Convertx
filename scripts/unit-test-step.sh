echo 'Executing Unit tests...'
echo 'Running Debug variant tests...'
./gradlew testDebugUnitTest
echo 'Debug variant tests DONE, starting Release variant tests...'
./gradlew testReleaseUnitTest
echo ' --- DONE performing Unit tests ---'
