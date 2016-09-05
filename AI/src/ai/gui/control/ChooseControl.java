package ai.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import ai.MainApp;
import ai.gui.model.Model;
import ai.swarm.mode.classes.Obstacle_Avoidance_Mode;
import ai.swarm.mode.classes.Open_World_Mode;
import ai.swarm.mode.classes.Path_Finding_Mode;
import ai.swarm.mode.classes.Pursuing_Mode;
import ai.swarm.mode.classes.Reynold_Rules_Mode;
import ai.swarm.mode.classes.Seek_and_Flee_Mode;
import ai.swarm.mode.classes.Wanderer_Mode;

/*
 * Die Klasse nimmt die Auswahl der JCombobox entgegen und leitet sie an das Model weiter
 */
public class ChooseControl implements ActionListener
{
	private Model model;
	private MainApp main;

	public ChooseControl(Model model, MainApp main)
	{
		this.model = model;
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String name = (String) ((JComboBox<String>) arg0.getSource())
				.getSelectedItem();

		model.getMyMode().close();
		switch (name)
		{
		case "Reynolds Rules":
			model.setNewMode(new Reynold_Rules_Mode());
			break;
		case "Seek and Flee":
			model.setNewMode(new Seek_and_Flee_Mode());
			break;
		case "Wanderer":
			model.setNewMode(new Wanderer_Mode());
			break;
		case "Path_Finding":
			model.setNewMode(new Path_Finding_Mode());
			break;
		case "obstacle avoidance":
			model.setNewMode(new Obstacle_Avoidance_Mode());
			break;
		case "Pursuing":
			model.setNewMode(new Pursuing_Mode());
			break;
		case "Open World":
			model.setNewMode(new Open_World_Mode());
		}

		main.requestFocus();

	}
}
