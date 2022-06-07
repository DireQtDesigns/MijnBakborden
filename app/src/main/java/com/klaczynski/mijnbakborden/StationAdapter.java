package com.klaczynski.mijnbakborden;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.klaczynski.mijnbakborden.objects.Platform;
import com.klaczynski.mijnbakborden.objects.Station;

import java.util.ArrayList;
import java.util.HashMap;

public class StationAdapter extends ArrayAdapter<Station> {
    private static final String TAG = "StationAdapter";

    public StationAdapter(Context context, ArrayList<Station> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Station station = getItem(position);
        HashMap<String, Platform> platforms = BakbordenLijstFragment.stationsMap.get(station.getAbbreviation()).getPlatforms();
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_station, parent, false);
        }
        // Lookup view for data population
        TextView stationAbbrev = convertView.findViewById(R.id.tvName);
        TextView tvP1 = convertView.findViewById(R.id.tvPlatform1);
        TextView tvP2 = convertView.findViewById(R.id.tvPlatform2);
        TextView tvP3 = convertView.findViewById(R.id.tvPlatform3);
        TextView tvP4 = convertView.findViewById(R.id.tvPlatform4);
        TextView tvP5 = convertView.findViewById(R.id.tvPlatform5);

        // Populate the data into the view using the data object
        stationAbbrev.setText(station.getAbbreviation());

        LinearLayout layout = (LinearLayout) convertView;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(station);
                Log.d(TAG, "onClick: clicked item: " + position);
            }
        });
        layout.setLongClickable(true);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Dialog viewDialog = new Dialog(getContext());
                viewDialog.setContentView(R.layout.dialog_delete);
                viewDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                viewDialog.show();
                Button btnYes = viewDialog.findViewById(R.id.buttonYes);
                Button btnNo = viewDialog.findViewById(R.id.buttonNo);

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BakbordenLijstFragment.stationsMap.remove(station.getAbbreviation());
                        ((BakbordenLijstFragment) FragmentManager.findFragment(layout)).syncData(true);
                        viewDialog.dismiss();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewDialog.dismiss();
                    }
                });
                return false;
            }
        });

        tvP1.setText("");
        tvP2.setText("");
        tvP3.setText("");
        tvP4.setText("");
        tvP5.setText("");

        //Check of station sporen heeft
        if (platforms == null || platforms.size() == 0) {
            tvP1.setText("Geen sporen gevonden");
            Log.e("StationAdapter", station.getFullName() + " heeft geen sporen!");
            return convertView;
        }

        //Eerste spoor
        Platform p1 = platforms.get(platforms.keySet().toArray()[0]);
        String p1Text = p1.getName();
        p1Text += p1.isA_to_b() ? " A -> B: ": " B -> A: ";
        p1Text += p1.bakbordenString();
        tvP1.setText(p1Text);

        //Tweede spoor
        if (platforms.size() > 1) {
            Platform p2 = platforms.get(platforms.keySet().toArray()[1]);
            String p2Text = p2.getName();
            p2Text += p2.isA_to_b() ? " A -> B: ": " B -> A: ";
            p2Text += p2.bakbordenString();
            tvP2.setText(p2Text);
        }

        //Derde spoor
        if (platforms.size() > 2) {
            Platform p3 = platforms.get(platforms.keySet().toArray()[2]);
            String p3Text = p3.getName();
            p3Text += p3.isA_to_b() ? " A -> B: ": " B -> A: ";
            p3Text += p3.bakbordenString();
            tvP3.setText(p3Text);
        }

        //Eventueel meer sporen, dan puntjes weergeven
        if (platforms.size() > 3) {
            tvP4.setText("Klik om meer weer te geven.");
            tvP5.setText("...");
        }

        return convertView;
        //Klaar in het geval deze niet eerder wordt geroepen
        //return convertView;


    }

    private void showDialog(Station s) {
        Dialog dialog = new Dialog(getContext());
        Log.d(TAG, "showDialog: " + getContext().toString());
        dialog.setContentView(R.layout.station_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView stationName = dialog.findViewById(R.id.dialogName);
        TextView buttonClose = dialog.findViewById(R.id.buttonDialogClose);
        TextView sp1 = dialog.findViewById(R.id.sp1);
        TextView sp2 = dialog.findViewById(R.id.sp2);
        TextView sp3 = dialog.findViewById(R.id.sp3);
        TextView sp4 = dialog.findViewById(R.id.sp4);
        TextView sp5 = dialog.findViewById(R.id.sp5);
        TextView sp6 = dialog.findViewById(R.id.sp6);
        TextView sp7 = dialog.findViewById(R.id.sp7);
        TextView sp8 = dialog.findViewById(R.id.sp8);
        TextView sp9 = dialog.findViewById(R.id.sp9);
        TextView sp10 = dialog.findViewById(R.id.sp10);
        TextView sp11 = dialog.findViewById(R.id.sp11);
        TextView sp12 = dialog.findViewById(R.id.sp12);

        sp1.setVisibility(TextView.GONE);
        sp2.setVisibility(TextView.GONE);
        sp3.setVisibility(TextView.GONE);
        sp4.setVisibility(TextView.GONE);
        sp5.setVisibility(TextView.GONE);
        sp6.setVisibility(TextView.GONE);
        sp7.setVisibility(TextView.GONE);
        sp8.setVisibility(TextView.GONE);
        sp9.setVisibility(TextView.GONE);
        sp10.setVisibility(TextView.GONE);
        sp11.setVisibility(TextView.GONE);
        sp12.setVisibility(TextView.GONE);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        stationName.setText(s.getFullName()+" ("+s.getAbbreviation()+")");
        HashMap<String, Platform> platforms = s.getPlatforms();

        Platform p1 = null;
        Platform p2 = null;
        Platform p3 = null;
        Platform p4 = null;
        Platform p5 = null;
        Platform p6 = null;
        Platform p7 = null;
        Platform p8 = null;
        Platform p9 = null;
        Platform p10 = null;
        Platform p11 = null;
        Platform p12 = null;

        if(platforms == null) {
            sp1.setVisibility(TextView.VISIBLE);
            sp1.setText("Geen sporen gevonden voor station "+s.getFullName()+"!");
            sp1.setTextColor(Color.RED);
            return;
        }

        if(platforms.size() > 0) p1 = platforms.get(platforms.keySet().toArray()[0]);
        if(platforms.size() > 1) p2 = platforms.get(platforms.keySet().toArray()[1]);
        if(platforms.size() > 2) p3 = platforms.get(platforms.keySet().toArray()[2]);
        if(platforms.size() > 3) p4 = platforms.get(platforms.keySet().toArray()[3]);
        if(platforms.size() > 4) p5 = platforms.get(platforms.keySet().toArray()[4]);
        if(platforms.size() > 5) p6 = platforms.get(platforms.keySet().toArray()[5]);
        if(platforms.size() > 6) p7 = platforms.get(platforms.keySet().toArray()[6]);
        if(platforms.size() > 7) p8 = platforms.get(platforms.keySet().toArray()[7]);
        if(platforms.size() > 8) p9 = platforms.get(platforms.keySet().toArray()[8]);
        if(platforms.size() > 9) p10 = platforms.get(platforms.keySet().toArray()[9]);
        if(platforms.size() > 10) p11 = platforms.get(platforms.keySet().toArray()[10]);
        if(platforms.size() > 11) p12 = platforms.get(platforms.keySet().toArray()[11]);

        if(p1 != null) {
            sp1.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p1.getName() + " " + (p1.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p1.getBakborden().size(); i++) {
                if (i != p1.getBakborden().size() - 1) {
                    spoorText += p1.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p1.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp1.setText(spoorText);
            sp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp1.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp1.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp1.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p2 != null) {
            sp2.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p2.getName() + " " + (p2.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p2.getBakborden().size(); i++) {
                if (i != p2.getBakborden().size() - 1) {
                    spoorText += p2.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p2.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp2.setText(spoorText);
            sp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp2.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp2.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp2.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p3 != null) {
            sp3.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p3.getName() + " " + (p3.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p3.getBakborden().size(); i++) {
                if (i != p3.getBakborden().size() - 1) {
                    spoorText += p3.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p3.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp3.setText(spoorText);
            sp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp3.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp3.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp3.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p4 != null) {
            sp4.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p4.getName() + " " + (p4.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p4.getBakborden().size(); i++) {
                if (i != p4.getBakborden().size() - 1) {
                    spoorText += p4.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p4.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp4.setText(spoorText);
            sp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp4.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp4.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp4.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p5 != null) {
            sp5.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p5.getName() + " " + (p5.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p5.getBakborden().size(); i++) {
                if (i != p5.getBakborden().size() - 1) {
                    spoorText += p5.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p5.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp5.setText(spoorText);
            sp5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp5.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp5.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp5.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p6 != null) {
            sp6.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p6.getName() + " " + (p6.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p6.getBakborden().size(); i++) {
                if (i != p6.getBakborden().size() - 1) {
                    spoorText += p6.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p6.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp6.setText(spoorText);
            sp6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp6.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp6.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp6.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p7 != null) {
            sp7.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p7.getName() + " " + (p7.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p7.getBakborden().size(); i++) {
                if (i != p7.getBakborden().size() - 1) {
                    spoorText += p7.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p7.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp7.setText(spoorText);
            sp7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp7.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp7.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp7.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p8 != null) {
            sp8.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p8.getName() + " " + (p8.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p8.getBakborden().size(); i++) {
                if (i != p8.getBakborden().size() - 1) {
                    spoorText += p8.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p8.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp8.setText(spoorText);
            sp8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp8.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp8.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp8.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p9 != null) {
            sp9.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p9.getName() + " " + (p9.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p9.getBakborden().size(); i++) {
                if (i != p9.getBakborden().size() - 1) {
                    spoorText += p9.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p9.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp9.setText(spoorText);
            sp9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp9.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp9.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp9.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p10 != null) {
            sp10.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p10.getName() + " " + (p10.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p10.getBakborden().size(); i++) {
                if (i != p10.getBakborden().size() - 1) {
                    spoorText += p10.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p10.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp10.setText(spoorText);
            sp10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp10.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp10.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp10.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p11 != null) {
            sp11.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p11.getName() + " " + (p11.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p11.getBakborden().size(); i++) {
                if (i != p11.getBakborden().size() - 1) {
                    spoorText += p11.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p11.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp11.setText(spoorText);
            sp11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp11.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp11.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp11.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
        if(p12 != null) {
            sp12.setVisibility(TextView.VISIBLE);
            String spoorText = "Spoor ";
            spoorText += p12.getName() + " " + (p12.isA_to_b() ? "A -> B: " : "B -> A: ");
            for (int i = 0; i < p12.getBakborden().size(); i++) {
                if (i != p12.getBakborden().size() - 1) {
                    spoorText += p12.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen") + ", ";
                } else {
                    spoorText += p12.getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk").replace("789", "Geen");
                }
            }
            sp12.setText(spoorText);
            sp12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sp12.getTypeface() == Typeface.DEFAULT_BOLD) {
                        sp12.setTypeface(Typeface.DEFAULT);
                    } else {
                        sp12.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                }
            });
        }
    }
}