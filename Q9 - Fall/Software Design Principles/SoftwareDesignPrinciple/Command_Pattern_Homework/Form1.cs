using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Input;

namespace Command_Pattern_Homework
{
    public partial class Form1 : Form
    {
        List<ICommandHomeWork> _stack = new List<ICommandHomeWork>();

        public Form1()
        {
            InitializeComponent();

            CustomButton Undo = new CustomButton(5, new CommandUndo(_stack));
            Undo.Click += new EventHandler(buttonClickNoHistory);
            this.Controls.Add(Undo);
            CustomButton ceebitty;

            ceebitty = new CustomButton(0, new CommandMove(0, -Undo.Height, Undo));
            ceebitty.Click += new EventHandler(buttonClick);
            this.Controls.Add(ceebitty);

            ceebitty = new CustomButton(1, new CommandMove(-(Undo.Width + 10), 0, Undo));
            ceebitty.Click += new EventHandler(buttonClick);
            this.Controls.Add(ceebitty);

            ceebitty = new CustomButton(2, new CommandMove((Undo.Width + 10), 0, Undo));
            ceebitty.Click += new EventHandler(buttonClick);
            this.Controls.Add(ceebitty);

            ceebitty = new CustomButton(3, new CommandMove(0, Undo.Height, Undo));
            ceebitty.Click += new EventHandler(buttonClick);
            this.Controls.Add(ceebitty);

            ceebitty = new CustomButton(4, new CommandCycleColor(Undo, 1));
            ceebitty.Click += new EventHandler(buttonClick);
            this.Controls.Add(ceebitty);


        }

        public void buttonClick(object sender, EventArgs yarrgs)
        {
            CustomButton Cibbity = (CustomButton)sender;
            Cibbity.GetCommand().execute();
            _stack.Add(Cibbity.GetCommand());
        }

        public void buttonClickNoHistory(object sender, EventArgs yarrgs)
        {
            CustomButton Cibbity = (CustomButton)sender;
            Cibbity.GetCommand().execute();
        }
    }
}
