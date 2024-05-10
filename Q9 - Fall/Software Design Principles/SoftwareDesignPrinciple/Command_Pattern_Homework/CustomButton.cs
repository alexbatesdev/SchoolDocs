using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Command_Pattern_Homework
{
    public class CustomButton : Button
    {
        private ICommandHomeWork _command;

        public CustomButton(int id, ICommandHomeWork command) : base()
        {
            if (command != null)
            {
                _command = command;
                base.Text = command.getDisplayText();
            }
            SetPosition(id);
        }

        private void SetPosition(int id)
        {
            System.Drawing.Point xyLoc;
            int xPos = 0;
            int yPos = 10;

            xPos = 10 + ((10 + base.Width) * id);
            xyLoc = new System.Drawing.Point(xPos, yPos);
            base.Location = xyLoc;
        }

        public ICommandHomeWork GetCommand()
        {
            return _command;
        }
    }
}
