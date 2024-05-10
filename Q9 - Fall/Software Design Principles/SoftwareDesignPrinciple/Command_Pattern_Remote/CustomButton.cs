using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Windows.Forms;

namespace Command_Pattern_Remote
{
     class CustomButton : Button
    {
        private ICommand _command = null;

        public CustomButton(uint id, ICommand command) : base()
        {
            base.Width = 200;
            base.Height = 30;

            if (command != null)
            {
                base.Text = command.getDesc();
                _command = command;
            }

            SetPos(id);
        }

        private void SetPos(uint id)
        {
            System.Drawing.Point xyLoc;
            int xPos = 0;
            int yPos = 0;

            if ((id >= 0) && (id < 10))
            {
                // Set button to left
                xPos = 10;
                yPos = (int)((id * (base.Height + 3)) + 30);
            }
            if ((id > 9) && (id < 20))
            {
                // Set button to right
                xPos = (int)(base.Width + 30);
                yPos = (int)(((id - 10) * (base.Height + 3)) + 30);
            }
            xyLoc = new System.Drawing.Point(xPos, yPos);
            base.Location = xyLoc;
        }

        public ICommand GetCommand()
        {
            return _command;
        }
    }
}
