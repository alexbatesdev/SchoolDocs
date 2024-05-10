using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace StopLight_State_Pattern
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        public State state = State.Red;
        public enum State
        {
            Red,
            Yellow,
            Green
        }

        private void nextButton_Click(object sender, EventArgs e)
        {
            switch (state)
            {
                case State.Red:
                    state = State.Green;
                    panel1.BackColor = Color.DarkRed;
                    panel3.BackColor = Color.Lime;
                    break;
                case State.Yellow:
                    state = State.Red;
                    panel1.BackColor = Color.Red;
                    panel2.BackColor = Color.Olive;
                    break;
                case State.Green:
                    state = State.Yellow;
                    panel2.BackColor = Color.Yellow;
                    panel3.BackColor = Color.DarkGreen;
                    break;
            }

        }
    }
}
