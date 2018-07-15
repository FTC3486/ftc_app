# OpModes
Welcome to the OpModes submodule of the Techno Warriors Advanced #3486 FTC Robotics repository! Here, we store season-specific opmodes and robot files.
# TeleOp Files
TeleOp files contain information about driver controlled robot movement using joysticks for the two-minute driver-controlled period of an FTC match. TeleOp files define the robot operations performed when each joystick button is pressed. The TeleOp files require Robot files which define robot hardware and subsystems.
# Autonomous Files
Autonomous files contain instructions to drive the robot without human control for the 30 autonomous period at the beginning of each FTC match. The autonomous files have access to rover subsystems for direct control and autodriver classes (in the RobotCoreExtensions submodule) can drive the rover using special sensors and logic.
# Robot Files
Robot files list and name the hardware pieces for each rover subsystem. The rover class packages the rover hardware into one class for reusability and controlled access for each subsystem.
