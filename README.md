# DefendX
The Intrusion Detection System (IDS) implemented in this project is a comprehensive security solution aimed at safeguarding network integrity and data integrity by monitoring various types of intrusions and anomalies. It operates by analyzing network traffic, device interactions, and file system activities to detect and respond to potential threats effectively.

Key Features:
DDoS Attack Detection:
The IDS continuously monitors network traffic patterns and behavior to detect Distributed Denial of Service (DDoS) attacks. It analyzes incoming traffic for abnormal patterns indicative of flooding or amplification attacks, and upon detection, triggers appropriate responses to mitigate the attack and maintain network availability.

USB Device Detection:
In addition to network-based threats, the IDS also monitors USB device insertions across endpoints within the network. It tracks USB device events and identifies unauthorized or suspicious devices being connected. This feature helps prevent unauthorized data exfiltration or the introduction of malicious payloads through USB-based attacks.

File Integrity Monitoring:
The IDS includes file integrity monitoring capabilities to detect unauthorized modifications or tampering with critical system files and user data. It monitors file attributes, checksums, and content changes to identify any alterations that may indicate a security breach or compromise. Upon detection, it alerts administrators and takes necessary actions to restore file integrity and prevent further unauthorized access.
