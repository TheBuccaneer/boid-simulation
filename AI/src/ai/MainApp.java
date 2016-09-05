package ai;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ai.gui.control.InputControl;
import ai.gui.model.Model;
import ai.gui.view.classes.Adjustment;
import ai.gui.view.classes.Choose;
import ai.gui.view.classes.Information;
import ai.gui.view.classes.Show;

public class MainApp extends JFrame
{

	public final static Color BACK = new Color(23, 23, 23);
	public final static Color FRONT = new Color(250, 250, 250);
	public final static String[] MODE_NAMES =
	{ "Reynolds Rules", "Seek and Flee", "Wanderer", "Path_Finding", "obstacle avoidance", "Pursuing", "Open World" };

	public MainApp()
	{
		Model model = new Model();
		this.initViews(model);
		this.initSettings(model);

	}

	private void initViews(Model m)
	{
		TitledBorder titled = null;

		JPanel root = new JPanel();
		JPanel setup = new JPanel();
		JPanel upper = new JPanel();

		setup.setBackground(BACK);

		Show show = new Show(m);
		Choose choose = new Choose(m, this);
		Adjustment adjustment = new Adjustment(m);
		Information information = new Information(m);

		titled = new TitledBorder("Chooser");
		titled.setTitleColor(FRONT);
		choose.setBorder(titled);

		titled = new TitledBorder("Adjustment");
		titled.setTitleColor(FRONT);
		adjustment.setBorder(titled);

		titled = new TitledBorder("Information");
		titled.setTitleColor(FRONT);
		information.setBorder(titled);

		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setup.setLayout(new BorderLayout());
		upper.setLayout(new BorderLayout());

		information.setPreferredSize(new Dimension(100, 100));
		upper.setPreferredSize(new Dimension(200, 100));
		setup.add(choose, BorderLayout.NORTH);
		setup.add(adjustment, BorderLayout.CENTER);
		upper.add(setup, BorderLayout.WEST);
		upper.add(show, BorderLayout.CENTER);
		root.add(upper);
		root.add(information);

		this.add(root);
	}

	private void initSettings(Model m)
	{
		this.setMinimumSize(new Dimension(1024, 768));
		this.setSize(new Dimension(1024, 768));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(new InputControl(m));
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new MainApp();

	}

}
