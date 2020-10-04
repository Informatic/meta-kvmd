SUMMARY = "Allwinner console image"
LICENSE = "MIT"
AUTHOR = "Dimitris Tassopoulos"

inherit allwinner-base-image

IMAGE_INSTALL += " \
    kvmd \
    v4l-utils \
    libgpiod-tools \
    kernel-module-uvcvideo \
"

# https://community.nxp.com/t5/i-MX-Processors/Yocto-initramfs/m-p/382738
