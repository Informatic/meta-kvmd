SUMMARY = "The main Pi-KVM daemon"
HOMEPAGE = "https://pikvm.org/"
AUTHOR = "Maxim Devaev <mdevaev@gmail.com>"
LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/pikvm/kvmd.git"
SRCREV = "081797b2536149b41ca513e64c20de8ee40f5b6e"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    bash \
    python3-pyyaml \
    python3-aiohttp \
    python3-aiofiles \
    python3-passlib \
    python3-pyserial \
    python3-psutil \
    python3-pygments \
    python3-pillow \
    \
    python3-logging \
    python3-asyncio \
    python3-netserver \
    python3-json \
"

#    python3-xlib
#    python3-pam
#    python3-pyghmi

inherit setuptools3
