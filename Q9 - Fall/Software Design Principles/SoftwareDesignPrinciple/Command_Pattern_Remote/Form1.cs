using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Command_Pattern_Remote
{
    public partial class Form1 : Form
    {
        List<ICommand> _stack = new List<ICommand>();

        public Form1()
        {
            InitializeComponent();

            CustomButton btCust = null;
            // Create custom button
            btCust = new CustomButton(0, new CommandBubblesOn());
            // Add click-event
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            // Add to display
            paneldepon.Controls.Add(btCust); // Adds button to our panel

            btCust = new CustomButton(10, new CommandBubblesOff());
            // Add click-event
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            // Add to display
            paneldepon.Controls.Add(btCust);


            btCust = new CustomButton(1, new CommandLightsOn(new API_Lights("TV")));
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            paneldepon.Controls.Add(btCust);


            btCust = new CustomButton(11, new CommandLightsOff(new API_Lights("TV")));
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            paneldepon.Controls.Add(btCust);


            btCust = new CustomButton(2, new CommandLightsOn(new API_Lights("sky")));
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            paneldepon.Controls.Add(btCust);


            btCust = new CustomButton(12, new CommandLightsOff(new API_Lights("sky")));
            btCust.Click += new System.EventHandler(this.btCommand_Click);
            paneldepon.Controls.Add(btCust);
        }

        private void btCommand_Click(object sender, EventArgs yarrgs)
        {
            // The sender is the item that called the even
            // We don't know it's our custom button until we cast it as such
            CustomButton cb = (CustomButton)sender;
            cb.GetCommand().execute();
            _stack.Add(cb.GetCommand());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (_stack.Count >= 1)
            {
                _stack[_stack.Count - 1].undo();
                _stack.RemoveAt(_stack.Count - 1);
            } else
            {
                Console.WriteLine("Nothing to undo");
            }
        }
    }
}
