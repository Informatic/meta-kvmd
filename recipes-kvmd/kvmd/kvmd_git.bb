SUMMARY = "The main Pi-KVM daemon"
HOMEPAGE = "https://pikvm.org/"
AUTHOR = "Maxim Devaev <mdevaev@gmail.com>"
LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/pikvm/kvmd.git \
           file://v2-hdmiusb-yocto.yaml \
           file://0001-yocto-adjustments.patch \
           "
SRCREV = "bfb54767fa41b671bc97e2709f1514c5e84b0a4c"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    bash \
    nginx \
    ustreamer \
    python3-pyyaml \
    python3-aiohttp \
    python3-aiofiles \
    python3-passlib \
    python3-pyserial \
    python3-psutil \
    python3-pygments \
    python3-pillow \
    libgpiod-python \
    \
    python3-logging \
    python3-asyncio \
    python3-netserver \
    python3-json \
    python3-multiprocessing \
    python3-mmap \
"


inherit useradd
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-u 989 -g 989 -d / -r -s /sbin/nologin kvmd"
GROUPADD_PARAM_${PN} = "-g 989 kvmd"


do_install_append() {
        install -d ${D}${sysconfdir}
        install -d ${D}${sysconfdir}/kvmd

        install -d ${D}${datadir}
        install -d ${D}${datadir}/kvmd

        install -m 0644 ${S}/configs/kvmd/auth.yaml ${D}${sysconfdir}/kvmd/auth.yaml
        install -m 0644 ${S}/configs/kvmd/logging.yaml ${D}${sysconfdir}/kvmd/logging.yaml
        install -m 0644 ${S}/configs/kvmd/meta.yaml ${D}${sysconfdir}/kvmd/meta.yaml
        install -m 0644 ${S}/configs/kvmd/override.yaml ${D}${sysconfdir}/kvmd/override.yaml

        install -m 0644 ${S}/configs/kvmd/ipmipasswd ${D}${sysconfdir}/kvmd/ipmipasswd
        install -m 0644 ${S}/configs/kvmd/vncpasswd ${D}${sysconfdir}/kvmd/vncpasswd
        install -m 0644 ${S}/configs/kvmd/htpasswd ${D}${sysconfdir}/kvmd/htpasswd

	cp -r ${S}/hid ${S}/web ${S}/extras ${S}/contrib/keymaps "${D}${datadir}/kvmd"

	mkdir -p ${D}${localstatedir}/lib/kvmd/msd/images
	mkdir -p ${D}${localstatedir}/lib/kvmd/msd/meta
	chown 989:989 ${D}${localstatedir}/lib/kvmd/msd/images ${D}${localstatedir}/lib/kvmd/msd/meta

	install -Dm444 -t "${D}/etc/kvmd/nginx" "${S}/configs/nginx"/*.conf
	chmod 644 "${D}/etc/kvmd/nginx/nginx.conf"

	install -d ${D}/${systemd_system_unitdir}
	install -m 0644 ${S}/configs/os/services/kvmd.service ${D}/${systemd_system_unitdir}
	install -m 0644 ${S}/configs/os/services/kvmd-otg.service ${D}/${systemd_system_unitdir}
	install -m 0644 ${S}/configs/os/services/kvmd-nginx.service ${D}/${systemd_system_unitdir}
	install -m 0644 ${S}/configs/os/services/kvmd-vnc.service ${D}/${systemd_system_unitdir}
	# FIXME install -m 0644 ${S}/configs/os/services/kvmd-ipmi.service ${D}/${systemd_system_unitdir}

	install -d ${D}/usr/lib/sysusers.d
	install -m 0644 ${S}/configs/os/sysusers.conf ${D}/usr/lib/sysusers.d/kvmd.conf

	install -d ${D}/usr/lib/tmpfiles.d
	install -m 0644 ${S}/configs/os/tmpfiles.conf ${D}/usr/lib/tmpfiles.d/kvmd.conf

	install -d ${D}${sysconfdir}/udev/rules.d

	# FIXME rpi4 ??
	install -m 0644 ${S}/configs/os/udev/v2-hdmiusb-rpi4.rules ${D}${sysconfdir}/udev/rules.d/kvmd.rules
	install -m 0644 ${WORKDIR}/v2-hdmiusb-yocto.yaml ${D}${sysconfdir}/kvmd/main.yaml
}

# todo: https://github.com/vitiral/gpio

#    python3-xlib
#    python3-pam
#    python3-pyghmi

inherit setuptools3

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "kvmd.service kvmd-otg.service kvmd-nginx.service kvmd-vnc.service"
# kvmd-ipmi.service

FILES_${PN} += " ${systemd_system_unitdir}/kvmd.service ${systemd_system_unitdir}/kvmd-otg.service ${systemd_system_unitdir}/kvmd-nginx.service ${systemd_system_unitdir}/kvmd-vnc.service ${systemd_system_unitdir}/kvmd-ipmi.service"
