using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Command_Pattern_Homework
{
    public class CommandMove : ICommandHomeWork
    {
        int _deltaX;
        int _deltaY;
        CustomButton _targetOfMovement;

        public CommandMove(int x, int y, CustomButton victim)
        {
            _targetOfMovement = victim;
            _deltaX = x;
            _deltaY = y;
        }

        public void execute()
        {
            System.Drawing.Point oldLocation = _targetOfMovement.Location;
            System.Drawing.Point newLocation = new System.Drawing.Point(oldLocation.X + _deltaX, oldLocation.Y + _deltaY);
            _targetOfMovement.Location = newLocation;
        }

        public void undo()
        {
            System.Drawing.Point oldLocation = _targetOfMovement.Location;
            System.Drawing.Point newLocation = new System.Drawing.Point(oldLocation.X - _deltaX, oldLocation.Y - _deltaY);
            _targetOfMovement.Location = newLocation;
        }

        public string getDisplayText()
        {
            if (_deltaX > 0 && _deltaY == 0) {
                return "Right";
            } 
            else if (_deltaX < 0 && _deltaY == 0)
            {
                return "Left";
            } 
            else if (_deltaY < 0 && _deltaX == 0)
            {
                return "Up";
            }
            else if (_deltaY > 0 && _deltaX == 0)
            {
                return "Down";
            } else
            {
                return "Diagonal";
            }
        }
    }
}
