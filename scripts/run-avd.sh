echo no | android create avd --force -n test -t "$1" --abi sys-img-armeabi-v7a-android-"$1"
emulator -avd test -no-audio -no-window &
android-wait-for-emulator
adb shell input keyevent 82 &
