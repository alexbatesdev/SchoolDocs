using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    internal class CommandLightsOff : ICommand
    {
        API_Lights _lightObject;

        public CommandLightsOff(API_Lights lightObject)
        {
            _lightObject = lightObject;
        }

        public void execute()
        {
            _lightObject.LightOff();
        }

        public string getDesc()
        {
            return "Turn the " + _lightObject.LightName() + " lights Off";
        }

        public void undo()
        {
            _lightObject.LightOn();
        }
    }
}
