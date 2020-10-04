SUMMARY = "The main Pi-KVM daemon"
HOMEPAGE = "https://pikvm.org/"
AUTHOR = "Maxim Devaev <mdevaev@gmail.com>"
LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/pikvm/ustreamer.git"
SRCREV = "2ad8871a54199561d38f894e45a6b65a82542507"
S = "${WORKDIR}/git"

# RDEPENDS_${PN} = "
DEPENDS = " \
libjpeg-turbo \
e2fsprogs \
libevent \
libbsd \
"

# libutil-linux
do_configure () {
    # Specify any needed configure commands here
    :
}

do_compile () {
    # You will almost certainly need to add additional arguments here
    oe_runmake
}

do_install () {
    # This is a guess; additional arguments may be required
    oe_runmake install 'DESTDIR=${D}' 'PREFIX=/usr'
}
