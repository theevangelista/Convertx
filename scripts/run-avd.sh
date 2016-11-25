echo no | android create avd --force -n test -t android-24 --abi armeabi-v7a
emulator -avd test -no-window -no-boot-anim &
adb wait-for-device
adb shell input keyevent 82 &
