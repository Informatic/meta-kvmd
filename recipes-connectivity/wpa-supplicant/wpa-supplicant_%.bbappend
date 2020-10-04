SYSTEMD_SERVICE_${PN}_remove = "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE_${PN} = "disable"

do_install_append() {
    rm -rf ${D}${sysconfdir}/wpa_supplicant*
}
