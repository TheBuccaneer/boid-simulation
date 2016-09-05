package ai.swarm.mode.classes.openWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ai.swarm.mode.classes.Open_World_Mode;

public class MyDialogListener implements ActionListener
{

	private Open_World_Mode owm;

	public MyDialogListener()
	{
		// TODO Auto-generated constructor stub
	}

	public MyDialogListener(Open_World_Mode owm)
	{
		this.owm = owm;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String name = ((JTextField) e.getSource()).getName();
		String v = e.getActionCommand();
		float value = 0.0f;

		try
		{
			value = Float.parseFloat(v);
		} catch (Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Bitte eine echte Zahl eingeben", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		switch (name)
		{
		case "sepDistFie":
			owm.getFlock().setSeperationDistance(value);
			break;
		case "neiDistFie":
			owm.getFlock().setNeighborhood_distance(value);
			break;
		case "sepFoFie":
			owm.getFlock().setSeperationForce(value);
			break;
		case "coFoFie":
			owm.getFlock().setCohesionForce(value);
			break;
		case "alFoFie":
			owm.getFlock().setAlignmentForce(value);
			break;
		case "seDiFie":
			owm.seek.setSeekingDistance(value);
			break;
		case "seSepFie":
			owm.seek.setSeekSeperationDistance(value);
			break;
		case "seFoFie":
			owm.seek.setSeekingForce(value);
			break;
		case "pathRadius":
			owm.path.setRadius(value);
			break;
		case "pathForce":
			owm.path.setForce(value);
		}

	}
}
