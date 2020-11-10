class Bracket:
    def __init__(self, character):
        self.character = character
        self.bracket_dict = {'[': ']', '{': '}', '(': ')', '"': '"'}

    def __str__(self) -> str:
        return(self.character)

    def display_bracket_dict(self):    
        return self.bracket_dict

def is_left_bracket(char, object):
    if char in Bracket.display_bracket_dict(object).keys():
        return True
    return False

def get_reverse(obj):
    for item in obj.bracket_dict:
        if obj.character == item:
            return obj.bracket_dict[obj.character]


def check_code(code):
    char_array = []
    possible_punct =  ['[', ']', '{', '}', '(', ')', '"', '"']
    punctuation_stack = []
    left_punctuation_stack = []
    for character in code:
        char_array.append(character)
    for character in char_array:
        if character in possible_punct:
            punctuation_stack.append(Bracket(character))
    for object in punctuation_stack:
        if is_left_bracket(object.character, object):
            left_punctuation_stack.append(object)
            continue
        if not is_left_bracket(object.character, object) and object.character == get_reverse(left_punctuation_stack[-1]):
            left_punctuation_stack.pop()
    if len(left_punctuation_stack) != 0:
        for object in left_punctuation_stack:
            print(f'Unmatched Closing Bracket/Quote \nNeed a Closing Bracket/Quote in your code for This Symbol: {object.character}')
        return False   
    return True
        

check_code('{}[rgsekjh][[[')
