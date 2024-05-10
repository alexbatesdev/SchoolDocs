using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Command_Pattern_Remote
{
    internal class CommandLightsOn : ICommand
    {
        API_Lights _lightObject;

        public CommandLightsOn(API_Lights lightObject)
        {
            _lightObject = lightObject;
        }

        public void execute()
        {
            _lightObject.LightOn();
        }

        public string getDesc()
        {
            return "Turn the " + _lightObject.LightName() + " lights On";
        }

        public void undo()
        {
            _lightObject.LightOff();
        }
    }
}
