using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Factory_Pattern_Homework
{
    public class ComponentDisplay
    {
        public ComponentDisplay() { }
        public Control Create(AbstractComponent component)
        {
            switch (component)
            {
                case BoxComponent box:
                    Panel panel = new Panel();
                    Label label = new Label();
                    label.Text = box._text;
                    panel.Controls.Add(label);
                    panel.Width = box._width;
                    panel.Height = box._height;
                    panel.BackColor = GetColor(box._backgroundColor);
                    panel.Left = box._left;
                    panel.Top = box._top;
                    return panel;
                case CheckComponent check:
                    CheckBox checkBox = new CheckBox();
                    checkBox.Text = check._text;
                    checkBox.Left = check._left;
                    checkBox.Top = check._top;
                    checkBox.Width = check._width;
                    checkBox.Height = check._height;
                    checkBox.BackColor = GetColor(check._backgroundColor);
                    return checkBox;
                case ButtonComponent button:
                    Button buttonControl = new Button();
                    buttonControl.Text = button._text;
                    buttonControl.Left = button._left;
                    buttonControl.Top = button._top;
                    buttonControl.Width = button._width;
                    buttonControl.Height = button._height;
                    buttonControl.BackColor = GetColor(button._backgroundColor);
                    return buttonControl;
                default:
                    Panel panelDefault = new Panel();
                    Label labelDefault = new Label();
                    labelDefault.Text = component._text;
                    panelDefault.Controls.Add(labelDefault);
                    panelDefault.Width = component._width;
                    panelDefault.Height = component._height;
                    panelDefault.BackColor = GetColor(component._backgroundColor);
                    panelDefault.Left = component._left;
                    panelDefault.Top = component._top;
                    return panelDefault;
            }
        }
        private static Color GetColor(string colorString)
        {
            Color color = Color.Transparent;
            switch (colorString)
            {
                case "Red":
                    color = Color.Red;
                    break;
                case "Orange":
                    color = Color.Orange;
                    break;
                case "Yellow":
                    color = Color.Yellow;
                    break;
                case "Green":
                    color = Color.Green;
                    break;
                case "Blue":
                    color = Color.Blue;
                    break;
                case "Indigo":
                    color = Color.Indigo;
                    break;
                case "Violet":
                    color = Color.Purple;
                    break;
                case "White":
                    color = Color.White;
                    break;
                case "Gray":
                    color = Color.Gray;
                    break;
                case "Black":
                    color = Color.Black;
                    break;
                case "Transparent": //Technically not needed
                    color = Color.Transparent;
                    break;
                default:
                    break;
            }
            return color;
        }
    }
}
