SUMMARY = "Allwinner console image"
LICENSE = "MIT"
AUTHOR = "Dimitris Tassopoulos"

inherit allwinner-base-tiny-image

IMAGE_INSTALL += " \
    kvmd \
"

# https://community.nxp.com/t5/i-MX-Processors/Yocto-initramfs/m-p/382738
