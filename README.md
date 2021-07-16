# patch-adams
Used to create a checksum patch file for use with https://github.com/mattcousineau/patches-ohoulihan

# Workflow:

- Build directory structure as you will have it on the remote server
- Place it in a top-level folder to simulate the server container
e.g.
```
top_level_folder   <-- drop patch-adams here
└── your_main_project_folder
    └── deeper
        └── deeper
```
- Compile and drop patchadams.jar in the top-level folder and run
- patch-adams will create a patchfile.txt with file checksums which can then be hosted for **patches-ohoulihan** to point to in config.properties patchfileurl 

