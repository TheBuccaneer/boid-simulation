package ai.gui.view.classes;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import ai.MainApp;
import ai.gui.control.ChooseControl;
import ai.gui.model.Model;
import ai.gui.view.MyView;

public class Choose extends JPanel implements MyView
{

	private Model model;
	private JComboBox<String> box;

	public Choose(Model model, MainApp main)
	{
		this.model = model;
		this.model.addMyView(this);
		this.setBackground(MainApp.BACK);
		this.initBox(main);
	}

	@Override
	public void changed()
	{
		// TODO Auto-generated method stub

	}

	private void initBox(MainApp main)
	{
		box = new JComboBox<String>(MainApp.MODE_NAMES);
		box.setSelectedItem(null);
		box.addActionListener(new ChooseControl(model, main));
		this.add(box);
	}

}
