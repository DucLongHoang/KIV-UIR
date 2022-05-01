from tkinter import *
import json
from os import listdir
from os.path import isfile, join, isdir, splitext
import sys
from PIL import Image, ImageTk


class Window(Frame):

    def __init__(self):
        super().__init__()
        self.data = self.load_json()
        self.last_picture_num = 0
        self.actual_picture_num = 0
        self.master.title("Annotation App")
        self.pack(fill=BOTH, expand=True)
        self.narr_area = Text(self, height=10)
        self.narr_area.grid(row=4, column=0, columnspan=6, rowspan=2,
                            padx=5, sticky=E + W)
        self.dial_area = Text(self, height=10)
        self.dial_area.grid(row=1, column=0, columnspan=6, rowspan=2,
                            padx=5, sticky=E + W)

        self.feedbackbutton = Button(self, text="FEEDBACK", bg="white", command=self.feedback_listener)
        self.feedbackbutton.grid(row=7, column=0)
        self.informbutton = Button(self, text="INFORM", bg="white", command=self.inform_listener)
        self.informbutton.grid(row=7, column=1)
        self.byebutton = Button(self, text="BYE", bg="white", command=self.bye_listener)
        self.byebutton.grid(row=7, column=2)
        self.greetbutton = Button(self, text="GREET", bg="white", command=self.greet_listener)
        self.greetbutton.grid(row=7, column=3)
        self.backchannelbutton = Button(self, text="BACKCHANNEL", bg="white", command=self.backchannel_listener)
        self.backchannelbutton.grid(row=7, column=4)
        self.notclassifiablebutton = Button(self, text="NOT CLASSIFIABLE", bg="white",
                                            command=self.nclassifiable_listener)
        self.notclassifiablebutton.grid(row=7, column=5)
        self.thankbutton = Button(self, text="THANK", bg="white", command=self.thank_listener)
        self.thankbutton.grid(row=8, column=0)
        self.closebutton = Button(self, text="CLOSE", bg="white", command=self.close_listener)
        self.closebutton.grid(row=8, column=1)
        self.politebutton = Button(self, text="POLITENESS_FORMULA", bg="white", command=self.polite_listener)
        self.politebutton.grid(row=8, column=2)
        self.commitbutton = Button(self, text="COMMIT", bg="white", command=self.commit_listener)
        self.commitbutton.grid(row=8, column=3)
        self.offerbutton = Button(self, text="OFFER", bg="white", command=self.offer_listener)
        self.offerbutton.grid(row=8, column=4)
        self.ynquestionbutton = Button(self, text="YES-NO QUESTION", bg="white", command=self.ynquestion_listener)
        self.ynquestionbutton.grid(row=8, column=5)
        self.whyquestionbutton = Button(self, text="WHY QUESTION", bg="white", command=self.whyquestion_listener)
        self.whyquestionbutton.grid(row=9, column=0)
        self.orquestionbutton = Button(self, text="OR QUESTION", bg="white", command=self.question_listener)
        self.orquestionbutton.grid(row=9, column=1)
        self.orderbutton = Button(self, text="ORDER", bg="white", command=self.order_listener)
        self.orderbutton.grid(row=9, column=2)
        self.errorbutton = Button(self, text="ERROR", bg="white", command=self.error_listener)
        self.errorbutton.grid(row=9, column=3)
        self.nodiabutton = Button(self, text="NO DIALOGUE", bg="white", command=self.nodia_listener)
        self.nodiabutton.grid(row=9, column=4)
        self.save_and_next = Button(self, text="Save >>", bg="lime", command=self.save_and_next_comic)
        self.save_and_next.grid(row=10, column=1)
        self.dialogue_boxes_button = Button(self, text="Dialogue", bg='lime', command=self.dialogue_button_listener)
        self.dialogue_boxes_button.grid(row=11, column=0)
        self.narration_boxeas_button = Button(self, text="Narration", bg='white', command=self.narration_button_listener)
        self.narration_boxeas_button.grid(row=11, column=1)
        self.face_boxes_button = Button(self, text="Faces", bg='white', command=self.faces_button_listener)
        self.face_boxes_button.grid(row=11, column=2)
        self.pressed_boxes_button = 1
        self.dialogue_back = Button(self, text="Back", bg='white', command=self.back_dialogue)
        self.dialogue_back.grid(row=12, column=0)
        self.narration_back = Button(self, text="Back", bg='white', command=self.back_narration)
        self.narration_back.grid(row=12, column=1)
        self.face_back = Button(self, text="Back", bg='white', command=self.back_faces)
        self.face_back.grid(row=12, column=2)
        self.actual_image_path = ""
        self.face_out_of_bounds = Button(self, text="Face OB", bg="white", command=self.out_of_bounds_face)
        self.face_out_of_bounds.grid(row=13, column=0)
        self.face_out_of_bounds_label = Label(self, text="Faces out of bounds: 0")
        self.face_out_of_bounds_label.grid(row=14, column=0)
        self.face_out_of_bounds_counter = 0
        # self.json = self.load_json()
        self.bounding_boxes_dia = []
        self.bounding_boxes_narr = []
        self.image_nm = ""
        self.path = ""
        self.pictures = []
        self.canvas = Canvas(self)
        self.canvas.bind("<ButtonPress-1>", self.on_button_press)
        self.canvas.bind("<ButtonRelease-1>", self.on_button_release)
        self.face_bounding_boxes = []
        self.error_code = 0
        self.start_x = 0
        self.start_y = 0
        if isfile("./data/progress.dat"):
            file = open("./data/progress.dat", 'r')
            num = int(file.read())
            self.last_picture_num = num
            self.actual_picture_num = num

    def write_into_json(self):
        with open("./data/annotation.json", 'w') as outfile:
            json.dump(self.data, outfile, indent=4)

        file = open("./data/progress.dat", 'w+')
        file.write(str(self.last_picture_num))
        file.close()

    def initUI(self, img_name):
        if img_name is None:
            Img_name = self.get_data()
        else:
            Img_name = img_name

        path = splitext(Img_name)[0]
        splitted = path.split("/")
        self.image_nm = splitted[len(splitted) - 1]
        self.actual_image_path = Img_name
        lbl_dialog = Label(self, padx=5, text="Dialog:")
        lbl_dialog.grid(column=0, row=0, sticky=W)

        lbl_naration = Label(self, padx=5, text="Narration:")
        lbl_naration.grid(row=3, column=0, sticky=W)

        lbl_anot = Label(self, padx=5, text="Annotation classes:")
        lbl_anot.grid(row=6, column=0, sticky=W)

        img = Image.open(Img_name)
        img = ImageTk.PhotoImage(img)
        panel = Label(self, image=img)
        panel.image = img
        background_image = img
        width = img.width()
        height = img.height()
        self.canvas.configure(width=width, height=height)
        self.canvas.create_image(width / 2, height / 2, image=background_image)
        self.canvas.grid(row=0, column=6, rowspan=8, sticky=W)

    def get_data(self):
        if len(self.pictures) == self.last_picture_num:
            print("done")
            exit(0)
        else:
            img_name = self.pictures[self.actual_picture_num]

        return self.path + "/" + img_name + ".jpg"

    def load_json(self):
        f = open("./data/annotation.json")
        return json.load(f)

    def save_and_next_comic(self):
        dialogues = self.dial_area.get(1.0, END)
        dialogues = dialogues.split("%")
        dsentences = []
        for dia in dialogues:
            sent = dia.strip()
            if sent != '':
                dsentences.append(dia.strip())

        narrations = self.narr_area.get(1.0, END)
        narrations = narrations.split("%")
        nsentences = []
        for dia in narrations:
            sent = dia.strip()
            if sent != '':
                nsentences.append(dia.strip())

        new_data = {"dialog": dsentences, "narration": nsentences, "img_id": self.image_nm,
                "dialog_bounding_boxes": self.bounding_boxes_dia.copy(),
                "narration_bounding_boxes": self.bounding_boxes_narr.copy(), "face_bounding_boxes": self.face_bounding_boxes.copy(),
                "error_code": self.error_code}

        self.data.append(new_data)
        self.last_picture_num += 1
        self.actual_picture_num += 1
        self.write_into_json()
        self.canvas.delete('all')
        self.error_code = 0
        self.face_out_of_bounds_counter = 0
        self.dial_area.delete(1.0, END)
        self.narr_area.delete(1.0, END)
        self.face_bounding_boxes.clear()
        self.bounding_boxes_dia.clear()
        self.bounding_boxes_narr.clear()
        self.pressed_boxes_button = 1
        self.dialogue_boxes_button.config(bg='lime')
        self.narration_boxeas_button.config(bg='white')
        self.face_boxes_button.config(bg='white')
        self.face_out_of_bounds_label.config(text="Faces out of bounds: 0", fg='black')
        self.start_y = 0
        self.start_x = 0
        if self.last_picture_num >= (len(self.pictures)):
            print("done")
            exit(0)
        img_name = self.get_data()
        self.initUI(img_name)

    def dialogue_button_listener(self):
        self.pressed_boxes_button = 1
        self.dialogue_boxes_button.config(bg='lime')
        self.narration_boxeas_button.config(bg='white')
        self.face_boxes_button.config(bg='white')

    def narration_button_listener(self):
        self.pressed_boxes_button = 2
        self.narration_boxeas_button.config(bg='lime')
        self.dialogue_boxes_button.config(bg='white')
        self.face_boxes_button.config(bg='white')

    def faces_button_listener(self):
        self.pressed_boxes_button = 3
        self.face_boxes_button.config(bg='lime')
        self.dialogue_boxes_button.config(bg='white')
        self.narration_boxeas_button.config(bg='white')

    def back_dialogue(self):
        if len(self.bounding_boxes_dia) > 0:
            self.face_out_of_bounds_counter = 0
            self.face_out_of_bounds_label.config(text="Faces out of bounds: 0", fg='black')
            self.bounding_boxes_dia.pop()
            img = Image.open(self.actual_image_path)
            img = ImageTk.PhotoImage(img)
            panel = Label(self, image=img)
            panel.image = img
            background_image = img
            width = img.width()
            height = img.height()
            self.canvas.configure(width=width, height=height)
            self.canvas.create_image(width / 2, height / 2, image=background_image)
            self.canvas.grid(row=0, column=6, rowspan=8, sticky=W)
            for box in self.bounding_boxes_dia:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="lime",
                                             width=2)
            for box in self.bounding_boxes_narr:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="blue",
                                             width=2)
            for box in self.face_bounding_boxes:
                if int(box[0]) < 0:
                    self.face_out_of_bounds_counter += 1
                    self.face_out_of_bounds_label.config(
                        text="Faces out of bounds: " + str(self.face_out_of_bounds_counter), fg='red')
                else:
                    self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="red",
                                                 width=2)

    def back_narration(self):
        if len(self.bounding_boxes_narr) > 0:
            self.face_out_of_bounds_counter = 0
            self.face_out_of_bounds_label.config(text="Faces out of bounds: 0", fg='black')
            self.bounding_boxes_narr.pop()
            img = Image.open(self.actual_image_path)
            img = ImageTk.PhotoImage(img)
            panel = Label(self, image=img)
            panel.image = img
            background_image = img
            width = img.width()
            height = img.height()
            self.canvas.configure(width=width, height=height)
            self.canvas.create_image(width / 2, height / 2, image=background_image)
            self.canvas.grid(row=0, column=6, rowspan=8, sticky=W)
            for box in self.bounding_boxes_dia:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="lime",
                                             width=2)
            for box in self.bounding_boxes_narr:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="blue",
                                             width=2)
            for box in self.face_bounding_boxes:
                if int(box[0]) < 0:
                    self.face_out_of_bounds_counter += 1
                    self.face_out_of_bounds_label.config(
                        text="Faces out of bounds: " + str(self.face_out_of_bounds_counter), fg='red')
                else:
                    self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="red",
                                                 width=2)

    def back_faces(self):
        if len(self.face_bounding_boxes) > 0:
            self.face_out_of_bounds_counter = 0
            self.face_out_of_bounds_label.config(text="Faces out of bounds: 0", fg='black')
            self.face_bounding_boxes.pop()
            img = Image.open(self.actual_image_path)
            img = ImageTk.PhotoImage(img)
            panel = Label(self, image=img)
            panel.image = img
            background_image = img
            width = img.width()
            height = img.height()
            self.canvas.configure(width=width, height=height)
            self.canvas.create_image(width / 2, height / 2, image=background_image)
            self.canvas.grid(row=0, column=6, rowspan=8, sticky=W)
            for box in self.bounding_boxes_dia:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="lime",
                                             width=2)
            for box in self.bounding_boxes_narr:
                self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="blue",
                                             width=2)
            for box in self.face_bounding_boxes:
                if int(box[0]) < 0:
                    self.face_out_of_bounds_counter += 1
                    self.face_out_of_bounds_label.config(
                        text="Faces out of bounds: " + str(self.face_out_of_bounds_counter), fg='red')
                else:
                    self.canvas.create_rectangle(int(box[0]), int(box[1]), int(box[2]), box[3], fill='', outline="red",
                                                 width=2)

    def out_of_bounds_face(self):
        self.face_bounding_boxes.append([-1.0, -1.0, -1.0, -1.0])
        self.face_out_of_bounds_counter += 1
        self.face_out_of_bounds_label.config(text="Faces out of bounds: " + str(self.face_out_of_bounds_counter), fg='red')

    def feedback_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<FEEDBACK " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def bye_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<BYE " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def inform_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<INFORM " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def greet_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<GREET " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def backchannel_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<BACKCHANNEL " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def nclassifiable_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<NOT_CLASSIFIABLE " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def thank_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<THANK " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def close_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<CLOSE " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def polite_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<POLITENESS_FORMULA " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def commit_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<COMMIT " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def offer_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<OFFER " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def ynquestion_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<YES-NO_QUESTION " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def whyquestion_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<WHY_QUESTION " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def question_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<OR_QUESTION " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def order_listener(self):
        if self.dial_area.selection_get():
            text = self.dial_area.get(1.0, END)
            selected_text = self.dial_area.selection_get()
            selected_text_anot = "<ORDER " + selected_text + ">"
            self.dial_area.replace(1.0, END, text.replace(selected_text, selected_text_anot))

    def error_listener(self):
        self.error_code = 1
        self.save_and_next_comic()

    def nodia_listener(self):
        self.error_code = 2
        self.save_and_next_comic()

    def on_button_press(self, event):
        self.start_x = self.canvas.canvasx(event.x)
        self.start_y = self.canvas.canvasy(event.y)

    def on_button_release(self, event):
        end_x = self.canvas.canvasx(event.x)
        end_y = self.canvas.canvasy(event.y)
        if self.pressed_boxes_button == 1:
            self.canvas.create_rectangle(self.start_x, self.start_y, end_x, end_y, fill='', outline="lime", width=2)
            self.bounding_boxes_dia.append([self.start_x, self.start_y, end_x, end_y])
        elif self.pressed_boxes_button == 2:
            self.canvas.create_rectangle(self.start_x, self.start_y, end_x, end_y, fill='', outline="blue", width=2)
            self.bounding_boxes_narr.append([self.start_x, self.start_y, end_x, end_y])
        elif self.pressed_boxes_button == 3:
            self.canvas.create_rectangle(self.start_x, self.start_y, end_x, end_y, fill='', outline="red", width=2)
            self.face_bounding_boxes.append([self.start_x, self.start_y, end_x, end_y])
        self.start_x = 0
        self.start_y = 0

    def init_pictures(self):
        onlyfiles = [f for f in listdir(self.path) if isfile(join(self.path, f))]
        stripped = [splitext(s)[0] for s in onlyfiles]
        self.pictures = sorted(stripped,
                               key=lambda k: (int(k.split("_")[0]), int(k.split("_")[1]), int(k.split("_")[2])))




def test_path(path):
    if isdir(path):
        onlyfiles = [f for f in listdir(path) if isfile(join(path, f))]
        for file in onlyfiles:
            try:
                Image.open(path + "/" + file)
            except IOError:
                print("Given folder must contain only pictures")
                exit(0)
        return True
    else:
        return False


def main(path):
    if test_path(path):
        root = Tk()
        root.geometry("1530x1010")
        app = Window()
        app.path = path
        app.init_pictures()
        app.initUI(None)
        root.mainloop()
    else:
        print("Can't find folder on given path: " + path)


if __name__ == '__main__':
    if len(sys.argv) == 2:
        main(sys.argv[1])
    else:
        print(
            "Wrong number of program arguments.\nRun program with command:\npython annotation_tool_UIR.py <path to "
            "pictures folder>\nor\npython3 annotation_tool_UIR.py <path to pictures folder>")
