SUMMARY = "Allwinner console image"
LICENSE = "MIT"
AUTHOR = "Dimitris Tassopoulos"

inherit allwinner-base-image

IMAGE_INSTALL += " \
    kvmd \
    kernel-module-uvcvideo \
"

# https://community.nxp.com/t5/i-MX-Processors/Yocto-initramfs/m-p/382738
