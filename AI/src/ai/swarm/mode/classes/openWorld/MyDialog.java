package ai.swarm.mode.classes.openWorld;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ai.MainApp;
import ai.swarm.mode.classes.Open_World_Mode;

/*
 * enthält alles für den Dialog für den Open World Mode
 */
public class MyDialog extends JDialog
{

	private JTextField seperationDistanceField = new JTextField("20");
	private JTextField neighborhood_distanceField = new JTextField("150");
	private JTextField seperationForceField = new JTextField("10.0");
	private JTextField cohesionForceField = new JTextField("0.03");
	private JTextField alignmentForceField = new JTextField("7.0");
	private JTextField seekingDistanceField = new JTextField("110.0");
	private JTextField seekSeperationDistanceField = new JTextField("70.0");
	private JTextField seekingForceField = new JTextField("0.2");
	private JTextField fleeingDistanceField = new JTextField("80.0");
	private JTextField fleeingForceField = new JTextField("10.0");
	private JTextField pathRadiusField = new JTextField("40");
	private JTextField pathForceField = new JTextField("5");

	private MyDialogListener mdl;

	public MyDialog(Open_World_Mode owm)
	{
		mdl = new MyDialogListener(owm);
		this.init();
	}

	private void init()
	{
		this.initReynold();
		this.initSeek();
		this.initFlee();
		this.initPath();
		this.initTextField();

		this.setSize(800, 400);
		this.setLayout(new GridLayout(2, 2));
		this.setLocationRelativeTo(null);
		this.setBackground(MainApp.BACK);
		this.pack();
		this.setVisible(true);

	}

	private void initPath()
	{
		JPanel panel = new JPanel();
		panel.setBackground(MainApp.BACK);
		GridLayout rootLayout = new GridLayout(0, 2);
		rootLayout.setVgap(5);
		rootLayout.setHgap(5);
		panel.setLayout(rootLayout);
		TitledBorder bb = new TitledBorder("Path");
		bb.setTitleColor(MainApp.FRONT);
		panel.setBorder(bb);

		JLabel pathRadiusLabel = new JLabel("Path Radius");
		JLabel pathForceLabel = new JLabel("Path Force");
		pathRadiusLabel.setForeground(Color.RED);
		pathForceLabel.setForeground(Color.RED);

		panel.add(pathRadiusLabel);
		panel.add(pathRadiusField);
		panel.add(pathForceLabel);
		panel.add(pathForceField);

		this.add(panel);

	}

	private void initFlee()
	{
		JPanel panel = new JPanel();
		panel.setBackground(MainApp.BACK);
		GridLayout rootLayout = new GridLayout(0, 2);
		rootLayout.setVgap(5);
		rootLayout.setHgap(5);
		panel.setLayout(rootLayout);
		TitledBorder bb = new TitledBorder("Flee");
		bb.setTitleColor(MainApp.FRONT);
		panel.setBorder(bb);

		JLabel fleeingDistanceLabel = new JLabel("Fleeing Distanz");
		JLabel fleeingForceLabel = new JLabel("Fleeing-Kraft");
		fleeingDistanceLabel.setForeground(Color.GREEN);
		fleeingForceLabel.setForeground(Color.GREEN);

		panel.add(fleeingDistanceLabel);
		panel.add(fleeingDistanceField);
		panel.add(fleeingForceLabel);
		panel.add(fleeingForceField);

		this.add(panel);

	}

	private void initReynold()
	{
		JPanel panel = new JPanel();
		panel.setBackground(MainApp.BACK);
		GridLayout rootLayout = new GridLayout(0, 2);
		rootLayout.setVgap(5);
		rootLayout.setHgap(5);
		panel.setLayout(rootLayout);
		TitledBorder bb = new TitledBorder("ReynoldRules");
		bb.setTitleColor(MainApp.FRONT);
		panel.setBorder(bb);

		JLabel seperationDistanceLabel = new JLabel("Seperations Radius");
		JLabel neighborhood_distanceLabel = new JLabel("Nachbarschafts Radius");
		JLabel seperationForceLabel = new JLabel("Seperations-Kraft");
		JLabel cohesionForceLabel = new JLabel("Zusammenhalts-Kraft");
		JLabel alignmentForceLabel = new JLabel("Ausrichtungs-Kraft");

		seperationDistanceLabel.setForeground(MainApp.FRONT);
		neighborhood_distanceLabel.setForeground(MainApp.FRONT);
		seperationForceLabel.setForeground(MainApp.FRONT);
		cohesionForceLabel.setForeground(MainApp.FRONT);
		alignmentForceLabel.setForeground(MainApp.FRONT);

		panel.add(seperationDistanceLabel);
		panel.add(seperationDistanceField);
		panel.add(neighborhood_distanceLabel);
		panel.add(neighborhood_distanceField);
		panel.add(seperationForceLabel);
		panel.add(seperationForceField);
		panel.add(cohesionForceLabel);
		panel.add(cohesionForceField);
		panel.add(alignmentForceLabel);
		panel.add(alignmentForceField);

		this.add(panel);

	}

	private void initSeek()
	{
		JPanel panel = new JPanel();
		panel.setBackground(MainApp.BACK);
		GridLayout rootLayout = new GridLayout(0, 2);
		rootLayout.setVgap(5);
		rootLayout.setHgap(5);
		panel.setLayout(rootLayout);
		TitledBorder bb = new TitledBorder("Seek");
		bb.setTitleColor(MainApp.FRONT);
		panel.setBorder(bb);

		JLabel seekingDistanceLabel = new JLabel("Seeking Max Radius");
		JLabel seekSeperationDistanceLabel = new JLabel("Seeking Inn. Grenze");
		JLabel seekingForceLabel = new JLabel("Seeking-Kraft");

		seekingDistanceLabel.setForeground(Color.YELLOW);
		seekSeperationDistanceLabel.setForeground(Color.YELLOW);
		seekingForceLabel.setForeground(Color.YELLOW);

		panel.add(seekingDistanceLabel);
		panel.add(seekingDistanceField);
		panel.add(seekSeperationDistanceLabel);
		panel.add(seekSeperationDistanceField);
		panel.add(seekingForceLabel);
		panel.add(seekingForceField);

		this.add(panel);
	}

	private void initTextField()
	{
		seperationDistanceField.addActionListener(mdl);
		neighborhood_distanceField.addActionListener(mdl);
		seperationForceField.addActionListener(mdl);
		cohesionForceField.addActionListener(mdl);
		alignmentForceField.addActionListener(mdl);
		seekingDistanceField.addActionListener(mdl);
		seekSeperationDistanceField.addActionListener(mdl);
		seekingForceField.addActionListener(mdl);
		fleeingDistanceField.addActionListener(mdl);
		fleeingForceField.addActionListener(mdl);
		pathRadiusField.addActionListener(mdl);
		pathForceField.addActionListener(mdl);

		seperationDistanceField.setName("sepDistFie");
		neighborhood_distanceField.setName("neiDistFie");
		seperationForceField.setName("sepFoFie");
		cohesionForceField.setName("coFoFie");
		alignmentForceField.setName("alFoFie");
		seekingDistanceField.setName("seDiFie");
		seekSeperationDistanceField.setName("seSepFie");
		seekingForceField.setName("seFoFie");
		fleeingDistanceField.setName("fleDist");
		fleeingForceField.setName("fleForce");
		pathRadiusField.setName("pathRadius");
		pathForceField.setName("pathForce");
	}

}
