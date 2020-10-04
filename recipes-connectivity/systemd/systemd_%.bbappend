do_install_append() {
	rm ${D}${sysconfdir}/systemd/network/wlan0.network
}
