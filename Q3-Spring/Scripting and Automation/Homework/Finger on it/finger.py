from PIL import Image as img
import os

list_ = os.listdir("images")

try:
    os.mkdir("new_images")
except:
    pass

count = 0
for file_in in list_:
    print(f"{str(count + 1)}/{str(len(list_))}")
    count += 1
    #splits at file extension
    file_name, ext = os.path.splitext(file_in)
    #Saves a string with our desired file name with new extension
    file_out = f"pic{count:04}.png"

    try:
        with img.open("images/" + file_in) as im:
            area = (0, 0, 200, 200)
            im = im.crop(area)
            im = im.transpose(img.Transpose.ROTATE_270)
            im = im.convert("L") # L + Ratio + didn't ask + I did ur mom + holy shit Scott the Woz??
            im.thumbnail((75, 75))
            im.save("new_images/" + file_out)
    except:
        print("Oop it didn't work lol \nTry running it again, sometimes it's just a fluke")


print("Complete")