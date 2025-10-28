# Section 2: 

## Lesson 3: Taking Control
- [ ] Wiring and controlling motors
- [ ] Initializing a motor controller in code
- [ ] Configuring a drivetrain
- [ ] 

### Motor Electrical Configuration

``` mermaid
---
title: Motor Wiring
config:
    flowchart:
        curve:
---
graph LR
    R[RoboRIO] <-- CAN Bus --> MC[Motor Controller];
    PDH[Power Delivery Hub] == Power ==> MC;
    MC == Power ==> M[Motor];
    M -- Feedback? --> MC;
```

The power to the motors used in FRC is regulated by a "motor contoller." There are a variety of motor controllers available, but the two that we used most commonly are the VictorSPX and the SparkMAX. The motor controller translates a command signal from the RoboRIO into the voltage supplied to the motor. If there are senors on the motor connected to the motor controller, it can also process those signals and provide feedback to the RoboRIO about the status of the motor.







## Lesson 4: Driving Lessons - Basic Teleop
The driver-controlled portion of the match is called `telop`. During this part of the game, the driver is primarily in control of the robot and commands are relayed from controllers attached to the drive station to the robot through the FMS (field managemnt system). Typically, the drive team consists of a driver, a mechanism operator, and a drive coach. 

### Inputs
The primary way that the drive and mechanism operator control the robot is via a [joystick or controller](https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html). Let's add a controller to the program so that we can operate the Romi.

> **JAVA Concept: `import`**
>
> Java and WPILib provide a lot of classes that you can use in your programs, but they aren't avaiable by default in your code. In order to access them, you have to `import` them into the `namespace` of your program. You do this by using the `import` statement followed by the path to the class that you want to import. You must import classes into any file that needs them. Typically, `import` statements are located at the top of a `.java` file, just after the `package` statement. 

> **NOTE**
> Before making any changes to your code, create a new branch.

The first step is to `import` the class so that we can interact with the controller. The class that we're going to use it the [`XboxController`](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/XboxController.html) class, which works with the Logitech controllers set to 'X' mode. It's located in `edu.wpi.first.wpilibj`. Add this line after the other imports at the top of the `Robot.java` file:

``` java
import edu.wpi.first.wpilibj.XboxController;
```
The next step is to create a new instance of the `XboxController` class. First, we have to define a variable that will hold the reference to the controller instance. We only want to have one reference to the joystick that we will re-use whenever we need to read values from the joystick. We'll do this inside the `Robot()` class before the constructor so that it is available to all the methods in the class. 

Find the line that instantiates the `RomiDrivetrain()`:
``` java
  private final RomiDrivetrain m_drivetrain = new RomiDrivetrain();
```
and add this line after it:
``` java
  private XboxController m_controller = new XboxController(0);
  (1)---- (2)----------- (3)--------- (4)--------------------
```
This line defines a member variable and initializes it to a new instance of the XboxController class. The individual parts of this statement do the following:
1. `private` is an optional keyword that says this variable can only be accessed within this class. This prevents other classes from reading or altering our controller.
2. `XboxController` defines the `type` of the variable. Every variable must have a specific `type` in Java, which specifies what type of object it can hold.
3. `m_controller` is the name of the variable. This is how our code will access the instance.
4. `= new XboxController(0)` creates a new instance by calling the constructor of the `XboxController` class and assigns it to the variable. In this case, we pass the constructor the value 0, which indicates that the controller will be connected to channel 0 of the driver station.

Finally, we need to connect the values we read from the controller with the inputs to the drivetrain. This should be done in the `teleopPeriodic()` method of the `Robot` class. This method is automatically called once every 20 ms when the robot is in `teleop` mode. Any code you put in here will run 50 times/second, so this is the place for the code you will use to operate the robot in `teleop`. 

Scroll down in the code until you see the definition of `teleopPeriodic()`. Currently this method does nothing. There is no code between the curly brackets `{` and `}`. Update the method to include the line between the curly brackets:

```java
    m_drivetrain.arcadeDrive(m_controller.getLeftY(), m_controller.getRightX());
```
Once you've done this, `teleopPeriodic()` should look like:

``` java
  @Override
  public void teleopPeriodic() {
    m_drivetrain.arcadeDrive(m_controller.getLeftY(), m_controller.getRightX());
  }
```
### Test the code
Now you're ready to test the code. 

First, power on the Romi and connect to it's WiFi access point. You should also plug in an XboxController into your computer for controlling the Romi.

You start the simulation by clicking on the WPILib icon above the code window and selecting `WPILib: Simulate Robot Code`. This will compile your code and let you know if there are any errors. If the code compiles, then it will open a `dashboard` called `glass` that shows you information about the robot status.
