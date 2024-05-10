using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Factory_Pattern_Homework
{
    internal class XMLLanguageFactory : LanguageFactoryAbstract
    {
        public override string Create(AbstractComponent component)
        {
            switch (component)
            {
                case BoxComponent boxComponent:
                    StringBuilder xmlBox = new StringBuilder();
                    xmlBox.Append("<component>\n");
                    xmlBox.Append("<style>\n");
                    xmlBox.Append($"position:absolute;background-color:{boxComponent._backgroundColor};");
                    xmlBox.Append($"width:{boxComponent._width}px;height:{boxComponent._height}px;");
                    xmlBox.Append($"left:{boxComponent._left}px;top:{boxComponent._top}px;\n");
                    xmlBox.Append("</style>\n");
                    xmlBox.Append("<text>\n");
                    xmlBox.Append(boxComponent._text + '\n');
                    xmlBox.Append("</text>\n");
                    xmlBox.Append("</component>");
                    return xmlBox.ToString();
                case CheckComponent checkComponent:
                    StringBuilder xmlCheckbox = new StringBuilder();
                    xmlCheckbox.Append("<component type='checkbox'>\n");
                    xmlCheckbox.Append("<style>\n");
                    xmlCheckbox.Append($"position:absolute;left:{checkComponent._left}px;top:{checkComponent._top}px;\n");
                    xmlCheckbox.Append("</style>\n");
                    xmlCheckbox.Append("<text>\n");
                    xmlCheckbox.Append(checkComponent._text + '\n');
                    xmlCheckbox.Append("</text>\n");
                    xmlCheckbox.Append("</component>");
                    return xmlCheckbox.ToString();
                case ButtonComponent buttonComponent:
                    StringBuilder xmlButton = new StringBuilder();
                    xmlButton.Append("<component type='button'>\n");
                    xmlButton.Append("<style>\n");
                    xmlButton.Append($"position:absolute;left:{buttonComponent._left}px;top:{buttonComponent._top}px;\n");
                    xmlButton.Append("</style>\n");
                    xmlButton.Append("<text>\n");
                    xmlButton.Append(buttonComponent._text + '\n');
                    xmlButton.Append("</text>\n");
                    xmlButton.Append("</component>");
                    return xmlButton.ToString();
                default:
                    StringBuilder xmlOutput = new StringBuilder();
                    xmlOutput.Append("<component>\n");
                    xmlOutput.Append("<style>\n");
                    xmlOutput.Append($"position:absolute;background-color:{component._backgroundColor};");
                    xmlOutput.Append($"width:{component._width}px;height:{component._height}px;");
                    xmlOutput.Append($"left:{component._left}px;top:{component._top}px;");
                    xmlOutput.Append("</style>\n");
                    xmlOutput.Append("<text>\n");
                    xmlOutput.Append(component._text + '\n');
                    xmlOutput.Append("</text>\n");
                    xmlOutput.Append("</component>");
                    return xmlOutput.ToString();
            }
        }
    }
}
